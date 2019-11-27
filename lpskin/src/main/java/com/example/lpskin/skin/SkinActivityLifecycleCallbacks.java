package com.example.lpskin.skin;

import android.app.Activity;
import android.app.Application;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;

import com.example.lpskin.skin.utils.SkinThemeUtils;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Carl on 2018/3/17 017.
 */

public class SkinActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    HashMap<Activity,SkinLayoutFactory> layoutFactoryHashMap = new HashMap<>();
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        SkinThemeUtils.updateStatusBar(activity);

        /**
         *  字体
         */
        Typeface typeface = SkinThemeUtils.getSkinTypeface(activity);

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        try {
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(layoutInflater, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SkinLayoutFactory skinLayoutFactory = new SkinLayoutFactory(activity,typeface);
        LayoutInflaterCompat.setFactory2(layoutInflater, skinLayoutFactory);
        //注册观察者
        SkinManager.getInstance().addObserver(skinLayoutFactory);
        layoutFactoryHashMap.put(activity,skinLayoutFactory);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        SkinLayoutFactory skinLayoutFactory = layoutFactoryHashMap.get(activity);
        SkinManager.getInstance().deleteObserver(skinLayoutFactory);
    }
}
