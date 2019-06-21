package com.example.lp.lpautosize.AutoSizeUtils.pixelautosize;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {
    private static ScreenUtils utils;
    //设计图宽高
    private static final float STANDARD_WIDTH = 720;
    private static final float STANDARD_HEIGHT = 1080;
    //屏幕显示宽高
    private int mDisplayWidth;
    private int mDisplayHeight;

    private ScreenUtils(Context context) {
        if (mDisplayWidth == 0 || mDisplayHeight == 0) {
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                manager.getDefaultDisplay().getMetrics(displayMetrics);
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    //横屏
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeight = displayMetrics.widthPixels - getStatusBarHeight(context);
                } else {
                    mDisplayWidth = displayMetrics.widthPixels;
                    mDisplayHeight = displayMetrics.heightPixels;
                }

            }
        }
    }

    /**
     * 获取系统ActionBar高度
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        int resID = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resID > 0) {
            return context.getResources().getDimensionPixelOffset(resID);
        }
        return 0;
    }

    public static ScreenUtils getInstance(Context context) {
        if (utils == null) {
            utils = new ScreenUtils(context.getApplicationContext());
        }
        return utils;
    }

    /**
     * 获取水平方向的缩放比例
     *
     * @return
     */
    public float getHorizontalScale() {
        return mDisplayWidth / STANDARD_WIDTH;
    }

    /**
     * 获取竖直方向的缩放比列
     * @return
     */
    public float getVerticalScale() {
        return mDisplayHeight / STANDARD_HEIGHT;
    }
}
