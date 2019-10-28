package com.example.lp.httpprc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lp.lpaccessibility.LpAccessBilityActivity;

import com.example.lp.lpautosize.AutoSizeActivity;
import com.example.lp.lpdesignpatterns.ImageLoaderPrc.ImageLoaderActivity;
import com.example.lp.lpglide.LpGlideActivity;

import com.example.lp.lphttp.LpHttpActivity;
import com.example.lp.lpmvp.ui.MvpMainActivity;
import com.example.lp.lpuicore.UiCoreActivity;
import com.example.lpreflect.annotation.ContentView;
import com.example.lpreflect.annotation.FindView;
import com.example.lpreflect.annotation.OnClick;
import com.tzutalin.dlib.LpFaceDetect;
import com.tzutalin.dlib.VisionDetRet;
import com.tzutalin.dlib.detect.CallbackListener;


import java.util.List;

import static com.example.lpreflect.InjectManager.inject;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MvpMainActivity";

    @FindView(R.id.bt_Lphttp)
    private Button bt_lphttp;

    private Button bt_lpglide,bt_accessbility,bt_testface,lp_autosize,lp_uicore,lp_imageloader,lp_mvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        inject(this);
        intView();
    }


    private void intView() {
        MainOnClicLitner mainOnClicLitner = new MainOnClicLitner();
        //bt_lphttp = findViewById(R.id.bt_Lphttp);
        bt_lpglide = findViewById(R.id.bt_LpGlide);
        bt_accessbility = findViewById(R.id.bt_LpAccessBility);
        bt_testface = findViewById(R.id.bt_testface);
        lp_autosize = findViewById(R.id.bt_LpAutoSize);
        lp_imageloader = findViewById(R.id.bt_imageloader);
        lp_uicore = findViewById(R.id.bt_uicore);
        lp_mvp = findViewById(R.id.bt_mvp);
        //bt_lphttp.setOnClickListener(mainOnClicLitner);
        bt_lpglide.setOnClickListener(mainOnClicLitner);
        bt_accessbility.setOnClickListener(mainOnClicLitner);
        bt_testface.setOnClickListener(mainOnClicLitner);
        lp_autosize.setOnClickListener(mainOnClicLitner);
        lp_uicore.setOnClickListener(mainOnClicLitner);
        lp_imageloader.setOnClickListener(mainOnClicLitner);
        lp_mvp.setOnClickListener(mainOnClicLitner);

    }

    @OnClick(R.id.bt_Lphttp)
    public void testOnClick(){
        Intent intent1 = new Intent(MainActivity.this, LpHttpActivity.class);
        startActivity(intent1);
    }

    private class MainOnClicLitner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
//                case R.id.bt_Lphttp:
//                   Intent intent1 = new Intent(MainActivity.this, LpHttpActivity.class);
//                    startActivity(intent1);
//                    break;
                case R.id.bt_LpGlide:
                    Intent intent2 = new Intent(MainActivity.this, LpGlideActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.bt_LpAccessBility:
                    Intent intent3 = new Intent(MainActivity.this, LpAccessBilityActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.bt_LpAutoSize:
                    Intent intent4 = new Intent(MainActivity.this, AutoSizeActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.bt_imageloader:
                    Intent intentImageloder = new Intent(MainActivity.this, ImageLoaderActivity.class);
                    startActivity(intentImageloder);
                    break;
                case R.id.bt_mvp:
                    Intent intentMvp = new Intent(MainActivity.this, MvpMainActivity.class);
                    startActivity(intentMvp);
                    break;
                case R.id.bt_testface:
                    CallbackListener callbackListener=new CallbackListener() {
                        @Override
                        public void onGetFace(List<VisionDetRet> results) {
                            Log.i(TAG, "onGetFace: "+results.size());

                        }

                        @Override
                        public void onNoFace(String detail) {
                            Log.i(TAG, "onNoFace: "+detail);

                        }
                    };
                    LpFaceDetect.getFaceDetRet(null,"/storage/emulated/0/news_article/8c942ec3d1420374de80db0b56717486.jpg",

                            callbackListener);

                    break;
                case R.id.bt_uicore:
                    Intent intentUicore = new Intent(MainActivity.this, UiCoreActivity.class);
                    startActivity(intentUicore);
                    break;

            }

        }
    }


}
