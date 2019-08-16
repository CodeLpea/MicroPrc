package com.example.lp.lpdesignpatterns.ImageLoaderPrc;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lp.lpdesignpatterns.ImageLoaderPrc.Loader.ImageLoader;
import com.example.lp.lpdesignpatterns.R;

public class ImageLoaderActivity extends AppCompatActivity {
    private String Url = "http://img1.juimg.com/180329/330819-1P32919115567.jpg";
    private Button bt_singe,bt_clear;
    private LinearLayout ll_scrollView;
    private  ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        imageLoader=new ImageLoader();
        intView();

    }

    private void intView() {
        ll_scrollView = findViewById(R.id.ll_sl);
        bt_singe = findViewById(R.id.bt_sigle);

        bt_clear = findViewById(R.id.bt_clear);
        ClicLisetner clicLisetner=new ClicLisetner();
        bt_singe.setOnClickListener(clicLisetner);

        bt_clear.setOnClickListener(clicLisetner);

    }

    private class ClicLisetner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int i = view.getId();
            if (i == R.id.bt_sigle) {
                showSinge();
            }
            else if (i == R.id.bt_clear) {
                ll_scrollView.removeAllViews();
            }

        }
    }



    private void showSinge() {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ll_scrollView.addView(imageView);
        imageLoader.displayImage(Url,imageView);
    }
}
