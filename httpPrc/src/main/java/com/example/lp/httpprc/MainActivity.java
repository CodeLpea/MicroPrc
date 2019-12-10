package com.example.lp.httpprc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lp.ServiceActivity;
import com.example.lp.lpaccessibility.LpAccessBilityActivity;

import com.example.lp.lpautosize.AutoSizeActivity;
import com.example.lp.lpdesignpatterns.ImageLoaderPrc.ImageLoaderActivity;
import com.example.lp.lpglide.LpGlideActivity;

import com.example.lp.lphttp.LpHttpActivity;
import com.example.lp.lpmvp.ui.MvpMainActivity;
import com.example.lp.lpuicore.UiCoreActivity;
import com.example.lpactivity.standard.StandActivity;
import com.example.lpnetstatus.NetStatusBus;
import com.example.lpnetstatus.annotation.NetSubscribe;
import com.example.lpnetstatus.annotation.type.Mode;
import com.example.lpnetstatus.annotation.type.NetType;
import com.example.lpnetstatus.annotation.type.ThreadWhere;
import com.example.lpreflect.annotation.ContentView;
import com.example.lpreflect.annotation.FindView;
import com.example.lpreflect.annotation.OnClick;
import com.example.lpskin.SplashActivity;
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

    @FindView(R.id.tv_netstatus)
    private TextView tv_net_status;

    private Button bt_lpglide,bt_accessbility,bt_testface,lp_autosize,lp_uicore,lp_imageloader,lp_mvp,bt_skin;

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
        bt_skin = findViewById(R.id.bt_skin);
        //bt_lphttp.setOnClickListener(mainOnClicLitner);
        bt_lpglide.setOnClickListener(mainOnClicLitner);
        bt_accessbility.setOnClickListener(mainOnClicLitner);
        bt_testface.setOnClickListener(mainOnClicLitner);
        lp_autosize.setOnClickListener(mainOnClicLitner);
        lp_uicore.setOnClickListener(mainOnClicLitner);
        lp_imageloader.setOnClickListener(mainOnClicLitner);
        lp_mvp.setOnClickListener(mainOnClicLitner);
        bt_skin.setOnClickListener(mainOnClicLitner);

    }

    @OnClick(R.id.bt_Lphttp)
    public void testOnClick(){
        Intent intent1 = new Intent(MainActivity.this, LpHttpActivity.class);
        startActivity(intent1);
    }
    @OnClick(R.id.bt_service)
    public void lpServiceMoudle(){
        startActivity(new Intent(MainActivity.this, ServiceActivity.class));
    }
    @OnClick(R.id.bt_activity)
    public void lpActivityMoudle(){
        startActivity(new Intent(MainActivity.this, StandActivity.class));
    }

    private class MainOnClicLitner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
//                case R.id.bt_Lphttp:
//                   Intent intent1 = new Intent(SkinMainActivity.this, LpHttpActivity.class);
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
                case R.id.bt_skin:
                    Intent intentSkin = new Intent(MainActivity.this, SplashActivity.class);
                    startActivity(intentSkin);
                    break;

            }

        }
    }


    @NetSubscribe(mode = Mode.AUTO,threadwhere = ThreadWhere.UI)
    private void netStatus(NetType netType){
        switch (netType) {
            case NONE:
                tv_net_status.setText("网络连接中断...");
                break;
            case WIFI:
                tv_net_status.setText("wifi已连接");
                break;
            case MOBILE:
                tv_net_status.setText("移动网络已连接");
                break;
            default:
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        NetStatusBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        NetStatusBus.getInstance().unRegister(this);
    }


}
