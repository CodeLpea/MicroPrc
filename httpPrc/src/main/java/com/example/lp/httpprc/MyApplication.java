package com.example.lp.httpprc;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.example.lpnetstatus.NetStatusBus;
import com.example.lpskin.skin.SkinManager;
import com.example.lpskin.skin.utils.SkinUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetStatusBus.getInstance().init(this);
        SkinManager.init(this);
        saveApk();
    }

    private void saveApk() {
        Log.e("MySkinApplication", "saveApk: ");
        String originPath = "/sdcard/app-skin-release.apk";
        String path = Environment.getExternalStorageDirectory() + "/" + "app-skin-release.apk";
        //两个路径是等效的
        SkinUtils.copyFilesFromAssets(this, "app-skin-release.apk", path);
    }
}
