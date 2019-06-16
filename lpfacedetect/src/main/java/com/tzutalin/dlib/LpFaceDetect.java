package com.tzutalin.dlib;

import android.graphics.Bitmap;
import android.util.Log;

import com.tzutalin.dlib.detect.CallbackListener;
import com.tzutalin.dlib.detect.FaceBitmpRequest;

/**
 * 人脸识别入口
 * lp
 * 2019/06/15
 * */
public class LpFaceDetect {
    private static FaceDet mFaceDet;
    public static void  getFaceDetRet(Bitmap bitmap, String bitmpPath, CallbackListener callbackListener){
        initFaceDet();
        new FaceBitmpRequest(mFaceDet,bitmap,bitmpPath,callbackListener).execute();
    }
    public static void initFaceDet(){
        if (mFaceDet == null) {
            Log.e("LpFaceDetect", "mFaceDet == null: ");
            mFaceDet = new FaceDet(Constants.getFaceShapeModelPath());
        }
    }
}
