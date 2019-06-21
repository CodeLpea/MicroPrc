package com.example.lp.lpautosize.AutoSizeUtils.pixelautosize;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
/**
 *自定义像素点适配方案
 * 只需要在ScreenUtils中设置设计高的像素宽高（px）
 * 并将顶层布局设置成ScreenAdapterLayout
 * 即可自适应
 * */
public class ScreenAdapterLayout extends RelativeLayout {
    private boolean flag;

    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!flag) {
            float scaleX = ScreenUtils.getInstance(getContext()).getHorizontalScale();
            float scaleY = ScreenUtils.getInstance(getContext()).getVerticalScale();

            int count = getChildCount();

            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scaleX);
                params.height = (int) (params.height * scaleY);
                params.leftMargin = (int) (params.leftMargin * scaleX);
                params.rightMargin = (int) (params.rightMargin * scaleX);
                params.topMargin = (int) (params.topMargin * scaleY);
                params.bottomMargin = (int) (params.bottomMargin * scaleY);

            }
            flag = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
