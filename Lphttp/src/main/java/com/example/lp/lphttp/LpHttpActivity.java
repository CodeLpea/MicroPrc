package com.example.lp.lphttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lp.ihttp.R;
import com.example.lp.lphttp.LpHttp.LpHttp;
import com.example.lp.lphttp.bean.testbean;
import com.example.lp.lphttp.response.LpResponseDataListener;

public class LpHttpActivity extends AppCompatActivity {
    private static final String TAG="LpHttpActivity";
    private String Url="http://www.kuaidi100.com/query?type=yuantong&postid=11111111111";
    private String Url2="http://www.kuaidi100.com/query?type=yuantong&postid=11111111111";
    private TextView tv_showResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lp_http_main);
        initView();
        testIHttp();
    }

    private void initView() {
        tv_showResponse=findViewById(R.id.tv_showResponse);
    }

    private void testIHttp() {
        LpHttp.sendJsonRequest(null, Url, testbean.class, new LpResponseDataListener() {
            @Override
            public void onSuccess(Object clazz) {
                Log.e(TAG, "onSuccess:clazz "+clazz.toString()) ;
                tv_showResponse.setText(clazz.toString());
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
