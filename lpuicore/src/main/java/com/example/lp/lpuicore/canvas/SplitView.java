package com.example.lp.lpuicore.canvas;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.lp.lpuicore.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.lp.lpuicore.Utils.BitmapUtils.zoomImage;

/**
 * 自定义爆炸效果View
 */
public class SplitView extends View {


    private static final String TAG = "SplitView";
    private Paint mPaint;
    private List<Ball> ballList = new ArrayList<>();
    private float d = 6;//半径

    private ValueAnimator mAnimator;


    public SplitView(Context context) {
        this(context, null);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.i(TAG, "init: ");
        mPaint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.testface);
        bitmap = zoomImage(bitmap, 80, 80);
        for (int i = 0; i < bitmap.getWidth(); i++) {//行
            for (int j = 0; j < bitmap.getHeight(); j++) {//列
                Ball ball = new Ball();
                //设置颜色
                ball.color = bitmap.getPixel(i, j);
                ball.x = i * d + d / 2;
                ball.y = j * d + d / 2;
                ball.r = d / 2;
                //初始速度(-20,20)
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangInt(-15, 35);
                //加速度
                ball.aX = 0;
                ball.aY = 0.98f;

                ballList.add(ball);

            }
        }

        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();
                invalidate();
            }
        });

    }

    private int rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j) - 1;
        //在0到(max - min)范围内变化，取大于x的最小整数 再随机
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    private void updateBall() {
        //更新粒子的位置
        for (Ball ball : ballList) {
            ball.x += ball.vX;
            ball.y += ball.vY;

            ball.vX += ball.aX;
            ball.vY += ball.aY;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(500, 500);


        for (Ball ball : ballList) {
            mPaint.setColor(ball.color);
            canvas.drawCircle(ball.x, ball.y, ball.r, mPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //执行动画
            mAnimator.start();

        }
        return super.onTouchEvent(event);

    }
}

