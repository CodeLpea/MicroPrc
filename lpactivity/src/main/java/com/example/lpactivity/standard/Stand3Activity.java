package com.example.lpactivity.standard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lpactivity.R;
import com.example.lpactivity.singleInstance.SingleInstanceActivity;
import com.example.lpactivity.singleTask.Task1Activity;
import com.example.lpactivity.singleTop.SingleTopActivity;

public class Stand3Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Stand----3----Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand3);
        Toast.makeText(this, "Stand----3----Activity", Toast.LENGTH_SHORT).show();
        findViewById(R.id.btn_tostand).setOnClickListener(this);
        findViewById(R.id.btn_tostand2).setOnClickListener(this);
        findViewById(R.id.btn_tostask1).setOnClickListener(this);
        findViewById(R.id.btn_tosingletop).setOnClickListener(this);
        findViewById(R.id.btn_tosingleinstance).setOnClickListener(this);
        findViewById(R.id.btn_destroy3).setOnClickListener(this);
        findViewById(R.id.btn_back3).setOnClickListener(this);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "-------------onNewIntent: ");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tostand) {
            Log.i(TAG, "----------btn_tostand: ");
            startActivity(new Intent(this, StandActivity.class));
        } else if (v.getId() == R.id.btn_tostand2) {
            Log.i(TAG, "----------btn_tostand2: ");
            startActivity(new Intent(this, Stand2Activity.class));
        } else if (v.getId() == R.id.btn_tostask1) {
            Log.i(TAG, "----------btn_tostask1: ");
            startActivity(new Intent(this, Task1Activity.class));
        }
        else if (v.getId() == R.id.btn_tosingletop) {
            Log.i(TAG, "----------btn_tosingletop: ");
            startActivity(new Intent(this, SingleTopActivity.class));
        }
        else if (v.getId() == R.id.btn_tosingleinstance) {
            Log.i(TAG, "----------btn_tosingletop: ");
            startActivity(new Intent(this, SingleInstanceActivity.class));
        }
        else if (v.getId() == R.id.btn_destroy3) {
            Log.i(TAG, "----------btn_destroy3: ");
            finish();
        } else if (v.getId() == R.id.btn_back3) {
            Log.i(TAG, "----------btn_back3: ");
            onBackPressed();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "-------------onStart: ");

    }
//
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
