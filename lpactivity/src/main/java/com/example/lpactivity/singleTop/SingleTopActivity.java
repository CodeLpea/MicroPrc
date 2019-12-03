package com.example.lpactivity.singleTop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lpactivity.R;
import com.example.lpactivity.singleInstance.SingleInstanceActivity;
import com.example.lpactivity.standard.StandActivity;

public class SingleTopActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SingleTop----1----";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);
        Log.e(TAG, "-------------onCreate: ");
        Toast.makeText(this, "SingleTopActivity", Toast.LENGTH_SHORT).show();
        findViewById(R.id.btn_tomain).setOnClickListener(this);
        findViewById(R.id.btn_tosingleinstance1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_tomain){
            startActivity(new Intent(this, StandActivity.class));
        }else if(v.getId()==R.id.btn_tosingleinstance1){
            startActivity(new Intent(this, SingleInstanceActivity.class));

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
