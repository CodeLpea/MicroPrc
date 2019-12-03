package com.example.lpactivity.standard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lpactivity.R;

public class StandActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Stand----1----Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand);
        Log.e(TAG, "-------------onCreate: ");
        Toast.makeText(this, "StandActivity", Toast.LENGTH_SHORT).show();
        findViewById(R.id.btn_tostand2).setOnClickListener(this);
        findViewById(R.id.btn_tostand3).setOnClickListener(this);
        findViewById(R.id.btn_destroy).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "-------------onNewIntent: ");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tostand2) {
            Log.i(TAG, "----------btn_tostand2: ");
            startActivity(new Intent(this, Stand2Activity.class));
        } else if (v.getId() == R.id.btn_tostand3) {
            Log.i(TAG, "----------btn_tostand3: ");
            startActivity(new Intent(this, Stand3Activity.class));
        } else if (v.getId() == R.id.btn_destroy) {
            Log.i(TAG, "----------btn_destroy: ");
            finish();
        } else if (v.getId() == R.id.btn_back) {
            Log.i(TAG, "----------btn_back: ");
            onBackPressed();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "-------------onStart: ");

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.e(TAG, "-------------onResume: ");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.e(TAG, "-------------onPause: ");
//    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "-------------onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "-------------onDestroy: ");
    }


}
