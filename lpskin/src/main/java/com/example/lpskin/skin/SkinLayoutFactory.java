package com.example.lpskin.skin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.lpskin.skin.utils.SkinThemeUtils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Carl on 2018/3/17 017.
 */

public class SkinLayoutFactory implements LayoutInflater.Factory2, Observer {
    private static final HashMap<String, Constructor<? extends View>> sConstructorMap =
            new HashMap<String, Constructor<? extends View>>();
    private static final String[] mClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };
    private final Activity activity;

    private SkinAttribute skinAttribute;

    public SkinLayoutFactory(Activity activity, Typeface typeface) {
        this.activity = activity;
        skinAttribute = new SkinAttribute(typeface);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = createViewFromTag(name, context, attrs);

        skinAttribute.load(view,attrs);
        return view;
    }

    private View createViewFromTag(String name, Context context, AttributeSet attrs) {
        //包含了. 自定义控件
        if (name.contains(".")) {
            return  createView(name,context,attrs);
        }
        for (String tag : mClassPrefixList) {
            View v = createView(tag + name, context, attrs);
            if (null == v)
                continue;
            return v;
        }
        return null;
    }

    private View createView(String name, Context context, AttributeSet attrs) {
        Constructor<? extends View> constructor = sConstructorMap.get(name);
        if (null == constructor) {
            try {
                Class<? extends View> aClass = context.getClassLoader().loadClass(name).asSubclass(View.class);
                constructor = aClass.getConstructor(Context.class, AttributeSet.class);
                sConstructorMap.put(name, constructor);
            } catch (Exception e) {
            }
        }
        if (null != constructor) {
            try {
                return constructor.newInstance(context, attrs);
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        SkinThemeUtils.updateStatusBar(activity);
        Typeface typeface = SkinThemeUtils.getSkinTypeface(activity);
        //更换皮肤
        skinAttribute.applySkin(typeface);
    }
}
