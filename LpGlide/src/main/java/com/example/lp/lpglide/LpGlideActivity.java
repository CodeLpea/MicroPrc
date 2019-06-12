package com.example.lp.lpglide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lp.lpglide.Request.RequestListener;

public class LpGlideActivity extends Activity {
    private String Url = "http://img1.juimg.com/180329/330819-1P32919115567.jpg";
    private String[] Urls = {Url, Url, Url, Url, Url, Url, Url, Url, Url, Url, Url, Url, Url};
    private Button bt_singe, bt_more,bt_stop,bt_clear;
    private LinearLayout ll_scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lpglide_activity_main);
        Toast.makeText(this, "LpGlideActivity", Toast.LENGTH_LONG).show();
        intView();

    }

    private void intView() {
        ll_scrollView = findViewById(R.id.ll_sl);
        bt_singe = findViewById(R.id.bt_sigle);
        bt_more = findViewById(R.id.bt_more);
        bt_stop = findViewById(R.id.bt_stop);
        bt_clear = findViewById(R.id.bt_clear);
        ClicLisetner clicLisetner=new ClicLisetner();
        bt_singe.setOnClickListener(clicLisetner);
        bt_more.setOnClickListener(clicLisetner);
        bt_stop.setOnClickListener(clicLisetner);
        bt_clear.setOnClickListener(clicLisetner);

    }

    private class ClicLisetner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int i = view.getId();
            if (i == R.id.bt_sigle) {
                showSinge();

            } else if (i == R.id.bt_more) {
                showMore();
            }
            else if (i == R.id.bt_stop) {
                LpGlide.Stop();
            }
            else if (i == R.id.bt_clear) {
                ll_scrollView.removeAllViews();
            }

        }
    }

    private void showMore() {
        for(String url:Urls){
            Log.e("showMore", "---------- ");
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ll_scrollView.addView(imageView);
            LpGlide.with(this).load(url)
                    .loading(R.mipmap.ic_launcher)
                    .listener(new RequestListener() {
                        @Override
                        public boolean onSuccess(Bitmap bitmap) {
                           /* Toast.makeText(LpGlideActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();*/
                            return false;
                        }

                        @Override
                        public boolean onFailure() {
                            return false;
                        }
                    }).Into(imageView);
        }

    }

    private void showSinge() {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ll_scrollView.addView(imageView);

        LpGlide.with(this).load(Url)
                .loading(R.mipmap.ic_launcher)
                .listener(new RequestListener() {
                    @Override
                    public boolean onSuccess(Bitmap bitmap) {
                        Toast.makeText(LpGlideActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onFailure() {
                        return false;
                    }
                }).Into(imageView);

    }
}
