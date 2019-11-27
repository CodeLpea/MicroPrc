package com.example.lpskin.skin;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;

import com.example.lpskin.skin.utils.SkinPreference;
import com.example.lpskin.skin.utils.SkinResources;

import java.lang.reflect.Method;
import java.util.Observable;

/**
 * 皮肤管理类
 *
 * */
public class SkinManager extends Observable{
    Application application;
    private static SkinManager instance;

    /**
     * 客户端程序在application的onCreate()后调用.
     * @param application
     */
    public static void init(Application application) {
        synchronized (SkinManager.class) {
            if (null == instance) {
                instance = new SkinManager(application);
            }
        }
    }

    public static SkinManager getInstance() {
        return instance;
    }

    private SkinManager(Application application) {
        this.application = application;
        SkinPreference.init(application);
        SkinResources.init(application);
        application.registerActivityLifecycleCallbacks(new SkinActivityLifecycleCallbacks());

        loadSkin(SkinPreference.getInstance().getSkin());
    }

    /**
     * 加载皮肤包 并 立即通知观察者更新
     *
     * @param path 皮肤包路径
     */
    public void loadSkin(String path) {
        if (TextUtils.isEmpty(path)) {
            SkinPreference.getInstance().setSkin("");
            SkinResources.getInstance().reset();
        } else {
            try {
                AssetManager assetManager = AssetManager.class.newInstance();
                // 添加资源进入资源管理器
                Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String
                        .class);
                addAssetPath.setAccessible(true);
                addAssetPath.invoke(assetManager, path);

                //app默认资源
                Resources resources = application.getResources();
                //皮肤包资源
                Resources skinResource = new Resources(assetManager, resources.getDisplayMetrics(),
                        resources.getConfiguration());
                //获取外部Apk(皮肤包) 包名
                PackageManager mPm = application.getPackageManager();
                PackageInfo info = mPm.getPackageArchiveInfo(path, PackageManager
                        .GET_ACTIVITIES);
                String packageName = info.packageName;
                SkinResources.getInstance().applySkin(skinResource, packageName);
                //保存当前使用的皮肤包
                SkinPreference.getInstance().setSkin(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //应用皮肤包
        setChanged();
        notifyObservers();
    }

}
