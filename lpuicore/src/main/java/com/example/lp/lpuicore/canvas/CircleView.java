package com.example.lp.lpuicore.canvas;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.lp.lpuicore.R;

/**
 * 自定义CirecleView:绘制一个动态的旋转然后扩散聚合的图
 * lp
 * 2019/7/23
 * */
public class CircleView extends View {
    private static final String TAG="CircleView";
    //小球的半径
    private float mBallr=18;

    //小球的颜色数组
    private int[] mCirecleColors;

    //小球的圆心坐标
    private float mBallx;
    private float mBally;

    //大圆的圆心坐标
    private float mCireclex;
    private float mCirecley;

    //旋转中的角度
    private float roteAngle;

    //大圆的初始半径
    private float mCirecleOriginr=90;

    //大圆的变化半径
    private float mCirecleChanger=mCirecleOriginr;

    private Paint mBallPaint;

    private int mBackGroundColor= Color.WHITE;

    //属性动画，用于旋转等操作
    private ValueAnimator mValueAnimator;
    private int mAnimatorTime=1200;


    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化
    private void init(){
        mBallPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirecleColors=getResources().getIntArray(R.array.splash_circle_colors);

    }

    private SplashState splashState;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(splashState==null){
            splashState=new RotateState();
        }
        splashState.drawState(canvas);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCireclex=w*1f/2;
        mCirecley=h*1f/2;
    }

    //抽象类
    private abstract class SplashState{
        abstract void drawState(Canvas canvas);
    }

    //画小球
    private void drawCircles(Canvas canvas) {
        //总共有六个小球，先获取每个小球的间隔角度
        float angle= (float) (Math.PI*2/mCirecleColors.length);
        //分别画每个小球
        for (int i = 0; i < mCirecleColors.length; i++) {
            //当前角度等于默认角度加上旋转角度
            float mAngle=i*angle+roteAngle;
            //小球的x坐标：大圆的半径R*cos（angle）+大圆的x坐标
            mBallx= (float) (mCirecleChanger*Math.cos(mAngle)+mCireclex);
            //小球的y坐标：大圆的半径R*sin（angle）+大圆的y坐标
            mBally= (float) (mCirecleChanger*Math.sin(mAngle)+mCirecley);
            mBallPaint.setColor(mCirecleColors[i]);
            canvas.drawCircle(mBallx,mBally,mBallr,mBallPaint);
        }

    }
    //画背景
    private void drawBackground(Canvas canvas) {
        canvas.drawColor(mBackGroundColor);
    }


    //旋转动画
    private class RotateState extends SplashState{

        private RotateState(){
            mValueAnimator=ValueAnimator.ofFloat(0,(float)(Math.PI*2));
            mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mAnimatorTime);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    roteAngle= (float) animation.getAnimatedValue();invalidate();

                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //监听动画结束，进行聚合扩散
                    splashState=new MergeState();
                }
            });
            //开始动画
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
            drawCircles(canvas);
        }
    }

    //扩散聚合
    private class MergeState extends SplashState{
        private MergeState(){
            mValueAnimator= ValueAnimator.ofFloat(mBallr,mCirecleOriginr);
            mValueAnimator.setDuration(mAnimatorTime);
            mValueAnimator.setInterpolator(new OvershootInterpolator(10f));
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCirecleChanger= (float) animation.getAnimatedValue();
                    invalidate();

                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                }
            });
            //开始动画,并在结束时候方向播放
            mValueAnimator.reverse();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
            //扩散聚合过程中，主要变化的是半径的变化
            drawCircles(canvas);
        }
    }






}
