package com.example.lpskin;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.example.lpskin.skin.SkinManager;
import com.example.lpskin.skin.utils.SkinUtils;


/**
 * @author Lance
 * @date 2018/3/8
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
        saveApk();
    }

    private void saveApk() {
        Log.e("MyApplication", "saveApk: ");
        String originPath="/sdcard/app-skin-release.apk";
        String path=Environment.getExternalStorageDirectory()+"/"+"app-skin-release.apk";
        //两个路径是等效的
        SkinUtils.copyFilesFromAssets(this,"app-skin-release.apk",path);
    }
}
