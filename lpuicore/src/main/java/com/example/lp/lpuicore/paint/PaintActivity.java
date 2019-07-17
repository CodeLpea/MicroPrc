package com.example.lp.lpuicore.paint;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.lp.lpuicore.R;

/**
 * Paint练习
 * lp
 * 2019/7/17
 * */
public class PaintActivity extends AppCompatActivity  {
    private RelativeLayout relativeLayout1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        initView();
    }

    private void initView() {
        relativeLayout1=findViewById(R.id.re_1);
        PaintView paintView=new PaintView(this);
        relativeLayout1.addView(paintView);
    }


}
