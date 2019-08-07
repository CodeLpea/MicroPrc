package com.example.lp.lpaccessibility.Utils;

import android.view.Display;
import android.view.WindowManager;

public class ScreenUtil {
    public  static Display getScreenDisplay(WindowManager windowManager){
        // 获取屏幕的默认分辨率
        Display display = windowManager.getDefaultDisplay();
        System.out.println("width-display :" + display.getWidth());
        System.out.println("heigth-display :" + display.getHeight());
        return display;
    }
}
