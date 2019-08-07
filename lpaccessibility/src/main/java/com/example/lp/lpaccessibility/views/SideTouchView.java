package com.example.lp.lpaccessibility.views;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.lp.lpaccessibility.R;
import com.example.lp.lpaccessibility.service.LpAccessibilityService;

import static android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK;
import static android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_HOME;
import static android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_RECENTS;
import static com.example.lp.lpaccessibility.Utils.ScreenUtil.getScreenDisplay;

/**
 * 自定义侧边栏
 *
 * */
public class SideTouchView extends View implements View.OnTouchListener {
    private LpAccessibilityService accessibilityService;
    private WindowManager.LayoutParams mParams;
    private LinearLayout mSideTouchView;
    public SideTouchView(Context context) {
        super(context);


    }

    public void getView(Context context,WindowManager windowManager, LpAccessibilityService sideBarService,boolean direction) {
        this.accessibilityService =sideBarService;

//        Display display=getScreenDisplay(windowManager);
        mParams = new WindowManager.LayoutParams();
        // compatible
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        // set bg transparent
        mParams.format = PixelFormat.RGBA_8888;
        // can not focusable
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.x = 0;
        mParams.y = 0;
        // window size
        mParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        // get layout
        LayoutInflater inflater = LayoutInflater.from(context);
        mSideTouchView = (LinearLayout) inflater.inflate(R.layout.layout_sidetouch, null);
        if(direction){
            mParams.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        }else {
            mParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        }

        mSideTouchView.setOnTouchListener(this);
        windowManager.addView(mSideTouchView,mParams);
    }



    float x1, x2, y1, y2;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float width=v.getWidth();
//        Log.i("onTouch", "getWidth"+width);

        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
//            Log.i("onTouch", " event.getX();: " + x1);
//            Log.i("onTouch", " event.getY();: " + y1);
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
         /*   //当手指移动的时候
            x2 = event.getX();
            y2 = event.getY();
            if (y1 - y2 > width) {
                Log.i("onTouch", "向上滑:就触发无障碍Home ");
                accessibilityService.performGlobalAction(GLOBAL_ACTION_HOME);
            } else if (y2 - y1 > width) {
                accessibilityService.performGlobalAction(GLOBAL_ACTION_RECENTS);
                Log.i("onTouch", "向下滑: 就触发无障碍多任务");
            } else if (x1 - x2 > width) {

               // Log.i("onTouch", "向左滑: ");
            } else if (x2 - x1 > width) {
                accessibilityService.performGlobalAction(GLOBAL_ACTION_BACK);
                Log.i("onTouch", "向右滑，就触发无障碍返回: ");
            }*/
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //  Log.i("onTouch", "抬手");//在抬手的时候处理按压的位置与抬手的位置的关系，从而达到全面屏手势的目的
            x2 = event.getX();
            y2 = event.getY();
            dealAction(x1,x2,y1,y2,width);

        }
        return false;
    }
    /**
     * 处理具体的手势
     * @param xStart x坐标开始
     * @param xEnd   x坐标结束
     * @param yStart y坐标开始
     * @param yEnd y坐标结束
     * */
    private void dealAction(float xStart,float xEnd,float yStart,float yEnd,float width){
        //判断X坐标开始的位置,如果小于横向的1/4，则表示在左侧滑动，向左滑才会生效返回手势
       /* Log.i("dealAction", " xStart " + xStart);
        Log.i("dealAction", " xEnd " + xEnd);
        Log.i("dealAction", " yStart " + yStart);
        Log.i("dealAction", " yEnd " + yEnd);*/
            if(Math.abs(xEnd-xStart)>width/2){
                //返回
                accessibilityService.performGlobalAction(GLOBAL_ACTION_BACK);
                Log.i("dealAction", "侧边返回: ");
            }


    }


}
