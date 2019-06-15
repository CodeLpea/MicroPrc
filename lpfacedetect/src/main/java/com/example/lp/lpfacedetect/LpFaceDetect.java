package com.example.lp.lpfacedetect;

import android.graphics.Bitmap;

import com.example.lp.lpfacedetect.detect.CallbackListener;
import com.example.lp.lpfacedetect.detect.FaceBitmpRequest;

/**
 * 人脸识别入口
 * lp
 * 2019/06/15
 * */
public class LpFaceDetect {
    public static void  getFaceDetRet(Bitmap bitmap, String bitmpPath, CallbackListener callbackListener){
        new FaceBitmpRequest(bitmap,bitmpPath,callbackListener).execute();
    }
}
