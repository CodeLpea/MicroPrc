package com.example.lp.httpprc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lp.httpprc.bean.testbean;
import com.example.lp.lpglide.LpGlideActivity;
import com.example.lp.lphttp.LpHttp;
import com.example.lp.lphttp.response.LpResponseDataListener;


public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private String Url="http://www.kuaidi100.com/query?type=yuantong&postid=11111111111";
    private String Url2="http://www.kuaidi100.com/query?type=yuantong&postid=11111111111";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testIHttp();
        Intent intent=new Intent(this, LpGlideActivity.class);
        startActivity(intent);
    }

    private void testIHttp() {
        LpHttp.sendJsonRequest(null, Url, testbean.class, new LpResponseDataListener() {
            @Override
            public void onSuccess(Object clazz) {
                Log.e(TAG, "onSuccess:clazz "+clazz.toString()) ;

            }

            @Override
            public void onFailure() {

            }
        });
    }
}
