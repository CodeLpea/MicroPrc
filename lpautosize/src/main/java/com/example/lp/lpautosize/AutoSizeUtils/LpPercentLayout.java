package com.example.lp.lpautosize.AutoSizeUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.lp.lpautosize.R;

public class LpPercentLayout extends RelativeLayout {
    public LpPercentLayout(Context context) {
        super(context);
    }

    public LpPercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LpPercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //测量容器宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height=View.MeasureSpec.getSize(heightMeasureSpec);
        //测量出子控件的宽高
        int childcount=getChildCount();
        for (int i=0;i<childcount;i++){
            View child=this.getChildAt(i);
            LayoutParams params= (LayoutParams) child.getLayoutParams();
            //默认不设置是为0
            float widthPercent=0;
            float heightPercent=0;
            float marginLeftPercent=0;
            float marginRightPercent=0;
            float marginTopPercent=0;
            float marginBottomPercent=0;
            if(params instanceof LpPercentLayout.LayoutParams){
                widthPercent=((LayoutParams) params).widthPercent;
                heightPercent=((LayoutParams) params).heightPercent;
                marginLeftPercent=((LayoutParams) params).marginLeftPercent;
                marginRightPercent=((LayoutParams) params).marginRightPercent;
                marginBottomPercent=((LayoutParams) params).marginBottomPercent;
                marginTopPercent=((LayoutParams) params).marginTopPercent;
                if(widthPercent!=0){
                    //不为0时重新设置宽度
                    params.width= (int) (width*widthPercent);
                }
                if(heightPercent!=0){
                    params.height= (int) (height*heightPercent);
                }
                if(marginLeftPercent!=0){
                    //不为0时重新设置宽度
                    ((LayoutParams) params).leftMargin= (int) (width*marginLeftPercent);
                }
                if(marginRightPercent!=0){
                    //不为0时重新设置宽度
                    ((LayoutParams) params).rightMargin= (int) (width*marginRightPercent);
                }
                if(marginBottomPercent!=0){
                    //不为0时重新设置宽度
                    ((LayoutParams) params).bottomMargin= (int) (width*marginBottomPercent);
                }
                if(marginTopPercent!=0){
                    //不为0时重新设置宽度
                    ((LayoutParams) params).topMargin= (int) (width*marginTopPercent);
                }

            }

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }


    public  static  class LayoutParams extends RelativeLayout.LayoutParams{
        //定义属性
        private float widthPercent;
        private float heightPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;


        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            //解析自己定义的属性
            TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.LpPercentLayout);
            widthPercent=array.getFloat(R.styleable.LpPercentLayout_widthPercent,0);
            heightPercent=array.getFloat(R.styleable.LpPercentLayout_widthPercent,0);
            marginLeftPercent=array.getFloat(R.styleable.LpPercentLayout_marginLeftPercent,0);
            marginTopPercent=array.getFloat(R.styleable.LpPercentLayout_marginTopPercent,0);
            marginBottomPercent=array.getFloat(R.styleable.LpPercentLayout_marginBottomPercent,0);
            marginRightPercent=array.getFloat(R.styleable.LpPercentLayout_marginRightPercent,0);
            array.recycle();

        }



    }
}
