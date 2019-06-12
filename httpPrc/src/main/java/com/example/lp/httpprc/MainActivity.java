package com.example.lp.httpprc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lp.lpglide.LpGlideActivity;
import com.example.lp.lphttp.LpHttpActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button bt_lphttp, bt_lpglide;

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
        bt_lphttp.setOnClickListener(mainOnClicLitner);
        bt_lpglide.setOnClickListener(mainOnClicLitner);

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
            }

        }
    }


}
