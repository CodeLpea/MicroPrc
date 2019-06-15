package com.example.lp.httpprc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lp.lpaccessibility.LpAccessBilityActivity;
import com.example.lp.lpaccessibility.service.LpAccessibilityService;
import com.example.lp.lpfacedetect.LpFaceDetect;

import com.example.lp.lpfacedetect.detect.CallbackListener;

import com.example.lp.lpfacedetect.detect.LpVisionDetRet;
import com.example.lp.lpglide.LpGlideActivity;
import com.example.lp.lphttp.LpHttpActivity;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button bt_lphttp, bt_lpglide,bt_accessbility,bt_testface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
    }


    private void intView() {
        MainOnClicLitner mainOnClicLitner = new MainOnClicLitner();
        bt_lphttp = findViewById(R.id.bt_Lphttp);
        bt_lpglide = findViewById(R.id.bt_LpGlide);
        bt_accessbility = findViewById(R.id.bt_LpAccessBility);
        bt_testface = findViewById(R.id.bt_testface);
        bt_lphttp.setOnClickListener(mainOnClicLitner);
        bt_lpglide.setOnClickListener(mainOnClicLitner);
        bt_accessbility.setOnClickListener(mainOnClicLitner);
        bt_testface.setOnClickListener(mainOnClicLitner);

    }

    private class MainOnClicLitner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_Lphttp:
                    Intent intent1 = new Intent(MainActivity.this, LpHttpActivity.class);
                    startActivity(intent1);

                    break;
                case R.id.bt_LpGlide:
                    Intent intent2 = new Intent(MainActivity.this, LpGlideActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.bt_LpAccessBility:
                    Intent intent3 = new Intent(MainActivity.this, LpAccessBilityActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.bt_testface:
                    CallbackListener callbackListener=new CallbackListener() {
                        @Override
                        public void onGetFace(List<LpVisionDetRet> results) {
                            Log.i(TAG, "onGetFace: "+results.size());

                        }

                        @Override
                        public void onNoFace(String detail) {
                            Log.i(TAG, "onNoFace: "+detail);

                        }
                    };
                    LpFaceDetect.getFaceDetRet(BitmapFactory.decodeResource(getResources(), R.drawable.testface),
                            null,
                            callbackListener);

                    break;
            }

        }
    }


}
