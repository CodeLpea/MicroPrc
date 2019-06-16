package com.tzutalin.dlib.detect;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;

import com.tzutalin.dlib.Constants;
import com.tzutalin.dlib.FaceDet;
import com.tzutalin.dlib.VisionDetRet;

import java.util.ArrayList;
import java.util.List;

/**
 * 人脸识别
 * lp
 */
public class FaceBitmpRequest {
    private static final String TAG = "FaceBitmpRequest";
    private Bitmap bitmap;
    private String path;
    private CallbackListener callbackListener;
    private FaceDet mFaceDet;
    private long spendTimes;


    public FaceBitmpRequest(FaceDet mFaceDet, Bitmap bitmap, String bitmpPath, CallbackListener callbackListener) {
        this.mFaceDet = mFaceDet;
        this.bitmap = bitmap;
        this.path = bitmpPath;
        this.callbackListener = callbackListener;
    }

    public void execute() {
        new AsyncTask<Void, Void, List<VisionDetRet>>() {
            @Override
            protected List<VisionDetRet> doInBackground(Void... voids) {

                // Init初始化第一次会比较忙，因此请事先调用一次，以便初始化
                if (mFaceDet == null) {
                    Log.e(TAG, "mFaceDet == null: ");
                    mFaceDet = new FaceDet(Constants.getFaceShapeModelPath());
                }
                List<VisionDetRet> results = null;
                spendTimes= System.currentTimeMillis();
                if (bitmap != null) {
                    results = mFaceDet.detect(bitmap);
                } else {
                    results = mFaceDet.detect(path);
                }
                Log.i(TAG, "Time=: "+(System.currentTimeMillis()-spendTimes));
                Log.i(TAG, "results: " + results.size());
                if (results != null && results.size() > 0) {
                    callbackListener.onGetFace(results);
                } else {
                    callbackListener.onNoFace("没找到人脸");
                }
                return results;
            }
        }.execute();


    }



    private void test(List<VisionDetRet> results) {
        for (final VisionDetRet ret : results) {
            String label = ret.getLabel();
            int rectLeft = ret.getLeft();
            int rectTop = ret.getTop();
            int rectRight = ret.getRight();
            int rectBottom = ret.getBottom();
            Log.i(TAG, "label: " + label);
            Log.i(TAG, "rectLeft: " + rectLeft);
            Log.i(TAG, "rectTop: " + rectTop);
            Log.i(TAG, "rectRight: " + rectRight);
            Log.i(TAG, "rectBottom: " + rectBottom);
            // Get 68 landmark points
            ArrayList<Point> landmarks = ret.getFaceLandmarks();
            for (Point point : landmarks) {
                int pointX = point.x;
                int pointY = point.y;
                Log.i(TAG, "pointX: " + pointX);
                Log.i(TAG, "pointy: " + pointY);
            }
        }
    }
}
