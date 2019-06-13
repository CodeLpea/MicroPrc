package com.example.lp.lpaccessibility.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import com.example.lp.lpaccessibility.views.SideTouchView;

public class LpAccessibilityService extends AccessibilityService {
    private static final String TAG="LpAccessibilityService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        createToucher();
    }

    private void createToucher() {
        WindowManager windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        SideTouchView sideTouchView=new SideTouchView(this);
        sideTouchView.getView(this,windowManager, this);
    }

}
