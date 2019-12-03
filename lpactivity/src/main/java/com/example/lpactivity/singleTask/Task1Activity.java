package com.example.lpactivity.singleTask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lpactivity.R;
import com.example.lpactivity.standard.Stand3Activity;
import com.example.lpactivity.standard.StandActivity;

public class Task1Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Task----1----";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
        Log.e(TAG, "-------------onCreate: ");
        Toast.makeText(this, "Task1Activity", Toast.LENGTH_SHORT).show();
        findViewById(R.id.btn_tostand1).setOnClickListener(this);
        findViewById(R.id.btn_tostand3).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tostand1) {
            Log.i(TAG, "----------btn_tostand1: ");
            startActivity(new Intent(this, StandActivity.class));
        } else if (v.getId() == R.id.btn_tostand3) {
            Log.i(TAG, "----------btn_destroy: ");
            startActivity(new Intent(this, Stand3Activity.class));
        } else if (v.getId() == R.id.btn_back) {
            Log.i(TAG, "----------btn_back: ");
            onBackPressed();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "-------------onNewIntent: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "-------------onStart: ");

    }

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
