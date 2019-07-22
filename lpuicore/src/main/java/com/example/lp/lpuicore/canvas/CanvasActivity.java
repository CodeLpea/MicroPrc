package com.example.lp.lpuicore.canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lp.lpuicore.R;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SplitView(this));
    }
}
