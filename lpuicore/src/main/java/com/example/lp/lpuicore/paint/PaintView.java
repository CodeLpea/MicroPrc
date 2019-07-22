package com.example.lp.lpuicore.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lp.lpuicore.R;

import static com.example.lp.lpuicore.Utils.BitmapUtils.zoomImage;

/**
 * 自定义的Paint展示View
 */
public class PaintView extends View {
    private static final String TAG="PaintView";
    private Paint mPaint;
    private Shader mShader;
    private Bitmap mBitmap;
    private int model = 0;

    /*第一构造方法，在代码中new这个View的时候会调用*/
    public PaintView(Context context) {
        super(context);
        Log.i(TAG, "PaintView: 第一构造方法");
        init();
    }

    /*第二构造方法，在xml布局文件中使用会调用*/
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /*第三构造方法，我们主动找第二构造函数中调用的，例如
     public PaintView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
             // this(context, attrs, R.attr.MyCustomViewDefStyleAttr);
            //就算使用super(context, attrs);也会自动调用第三构造方法，因为在父类View中，默认使用this(context, attrs, 0)
        }
    用于给出主题style
    主题中如果对defStyleAttr属性进行赋值，显示对defStyleAttr的赋值，优先级最高！
    */
    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        Log.i(TAG, "PaintView: 第三构造方法");
    }

    /*初始化*/
    private void init() {
        Log.i(TAG, "init: ");
        mPaint = new Paint();
        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.testface);
        mBitmap=zoomImage(mBitmap,360,240);
        setmPaintAbout();

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: ");
        switch (model) {
            case 0:
                Log.i(TAG, "onDraw:model:0 ");
                LinearRendering(canvas);
                break;
            default:
                Log.i(TAG, "onDraw: default");
                break;
        }


    }

    /*线性渲染*/
    private void LinearRendering(Canvas canvas) {
        Log.i(TAG, "LinearRendering: ");
        /**
         * 1.线性渲染,LinearGradient(float x0, float y0, float x1, float y1, @NonNull @ColorInt int colors[], @Nullable float positions[], @NonNull TileMode tile)
         * (x0,y0)：渐变起始点坐标
         * (x1,y1):渐变结束点坐标
         * color0:渐变开始点颜色,16进制的颜色表示，必须要带有透明度
         * color1:渐变结束颜色
         * colors:渐变数组
         * positions:位置数组，position的取值范围[0,1],作用是指定某个位置的颜色值，如果传null，渐变就线性变化。
         * tile:用于指定控件区域大于指定的渐变区域时，空白区域的颜色填充方法
         */
        mShader = new LinearGradient(0, 0, 500, 500, new int[]{Color.RED, Color.BLUE, Color.GREEN}, new float[]{0.f, 0.7f, 1}, Shader.TileMode.REPEAT);
        mPaint.setShader(mShader);
//        canvas.drawCircle(250, 250, 250, mPaint);
        canvas.drawRect(0, 0, 1000, 1000, mPaint);

    }

    /*环形渲染*/
    private void CircleRendering(Canvas canvas) {
        /**
         * 环形渲染，RadialGradient(float centerX, float centerY, float radius, @ColorInt int colors[], @Nullable float stops[], TileMode tileMode)
         * centerX ,centerY：shader的中心坐标，开始渐变的坐标
         * radius:渐变的半径
         * centerColor,edgeColor:中心点渐变颜色，边界的渐变颜色
         * colors:渐变颜色数组
         * stoops:渐变位置数组，类似扫描渐变的positions数组，取值[0,1],中心点为0，半径到达位置为1.0f
         * tileMode:shader未覆盖以外的填充模式。
         */
        mShader = new RadialGradient(500, 500, 250, new int[]{Color.GREEN, Color.YELLOW, Color.RED}, null, Shader.TileMode.MIRROR);
        mPaint.setShader(mShader);
        canvas.drawCircle(500, 500, 500, mPaint);

    }

    /*扫描渲染*/
    private void ScannRendering(Canvas canvas) {
        /**
         * 扫描渲染，SweepGradient(float cx, float cy, @ColorInt int color0,int color1)
         * cx,cy 渐变中心坐标
         * color0,color1：渐变开始结束颜色
         * colors，positions：类似LinearGradient,用于多颜色渐变,positions为null时，根据颜色线性渐变
         */
        mShader = new SweepGradient(250, 250, Color.RED, Color.GREEN);
        mPaint.setShader(mShader);
        canvas.drawCircle(250, 250, 250, mPaint);
    }

    /*位图渲染*/
    private void BitmapRendering(Canvas canvas) {
        /**
         * 位图渲染，BitmapShader(@NonNull Bitmap bitmap, @NonNull TileMode tileX, @NonNull TileMode tileY)
         * Bitmap:构造shader使用的bitmap
         * tileX：X轴方向的TileMode
         * tileY:Y轴方向的TileMode
         REPEAT, 绘制区域超过渲染区域的部分，重复排版
         CLAMP， 绘制区域超过渲染区域的部分，会以最后一个像素拉伸排版
         MIRROR, 绘制区域超过渲染区域的部分，镜像翻转排版
         */

        mShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        mPaint.setShader(mShader);
        canvas.drawRect(0, 0, 1000, 1000, mPaint);
//        canvas.drawCircle(250, 250, 250, mPaint);
    }

    /*组合渲染*/
    private void CombinedRendering(Canvas canvas) {
        /**
         * 组合渲染，
         * ComposeShader(@NonNull Shader shaderA, @NonNull Shader shaderB, Xfermode mode)
         * ComposeShader(@NonNull Shader shaderA, @NonNull Shader shaderB, PorterDuff.Mode mode)
         * shaderA,shaderB:要混合的两种shader
         * Xfermode mode： 组合两种shader颜色的模式
         * PorterDuff.Mode mode: 组合两种shader颜色的模式
         */
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        LinearGradient linearGradient = new LinearGradient(0, 0, 1000, 1600, new int[]{Color.RED, Color.GREEN, Color.BLUE}, null, Shader.TileMode.CLAMP);
        mShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
        mPaint.setShader(mShader);
        canvas.drawCircle(250, 250, 250, mPaint);

    }

    /*控制画笔*/
    public void setModel(int model) {
        this.model = model;
    }


    private void setmPaintAbout() {
        Log.i(TAG, "setmPaintAbout: ");
        /*Paint的常用属性*/
        //设置颜色
        mPaint.setColor(Color.RED);
        //设置效果，FILL为填充效果，忽略所有与笔画相关的设置
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        //设置Paint对象ARGB颜色
//        mPaint.setARGB(255, 255, 255, 0);
//        //设置透明度，范围0-255
//        mPaint.setAlpha(100);
//
//
//        //设置是否开启抗锯齿
//        mPaint.setAntiAlias(true);
//        //描边的宽度，只在style为STROKE模式下生效
//        mPaint.setStrokeWidth(5);
//        //圆角风格，头尾划线的地方采用什么封闭风格
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        //拐角风格，划线转弯的地方的风格
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//
//
//        //设置文本缩放倍数
//        mPaint.setTextScaleX(2);
//        //设置文字的大小
//        mPaint.setTextSize(20);
//        //设置文字的对齐方式
//        mPaint.setTextAlign(Paint.Align.LEFT);
//        //设置下划线
//        mPaint.setUnderlineText(true);
//        //Rect使用,测量文字大小，大小信息方在rect中
//        String text="Paint的Rect使用";
//        Rect rect=new Rect();
//        mPaint.getTextBounds(text,0,text.length(),rect);
//        //获取文字的Metrics信息
//        Paint.FontMetrics fontMetrics=new Paint.FontMetrics();
//        fontMetrics=mPaint.getFontMetrics();
//        //获取文字的宽度
//        float textWith=mPaint.measureText(text);
//
//
//
//
//        //设置环形渲染器
//        mPaint.setShader(new SweepGradient(200,200,Color.BLACK,Color.RED));
//        //设置图层混合模式
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
//        //设置颜色过滤器
//        mPaint.setColorFilter(new LightingColorFilter(0x00ffff,0x000000));
//        //设置双线性过滤
//        mPaint.setFilterBitmap(true);
//        //设置画笔遮罩滤镜，传入度数和样式
//        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));


    }



}
