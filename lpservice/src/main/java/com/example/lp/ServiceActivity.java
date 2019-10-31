package com.example.lp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity {
    private static final String TAG="ServiceActivity";
    private Button btn_start,btn_stop,btn_bind,btn_unbind;
    private ClickListener clickListener=new ClickListener();
    private Context context=this;

    private BindServiceTest.Mybinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initViews();
    }

    private void initViews() {
        btn_start=findViewById(R.id.btn_start_service);
        btn_stop=findViewById(R.id.btn_stop_service);
        btn_bind=findViewById(R.id.btn_bind_service);
        btn_unbind=findViewById(R.id.btn_unbind_service);
        btn_start.setOnClickListener(clickListener);
        btn_stop.setOnClickListener(clickListener);
        btn_bind.setOnClickListener(clickListener);
        btn_unbind.setOnClickListener(clickListener);

    }

    private  class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent serviceIntent=new Intent(context,BindServiceTest.class);
            if(v.getId()==R.id.btn_start_service){
                Log.i(TAG, "------------startService: ");
                startService(serviceIntent);
            }else if(v.getId()==R.id.btn_stop_service){
                Log.i(TAG, "------------stopService: ");
                stopService(serviceIntent);
            }
            else if(v.getId()==R.id.btn_bind_service){
                Log.i(TAG, "------------bindService: ");
                bindService(serviceIntent,connectionn,BIND_AUTO_CREATE);
            }
            else if(v.getId()==R.id.btn_unbind_service){
                if(myBinder!=null){
                    Log.i(TAG, "------------unbindService: ");
                    myBinder=null;
                    unbindService(connectionn);
                }
            }
            else {
                Log.i(TAG, "-------------onClick: ");
            }

        }
    }
    ServiceConnection connectionn= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder= (BindServiceTest.Mybinder) service;
            String binderInfo = myBinder.getBinderInfo();
            Log.i(TAG, "onServiceConnected: binderInfo==========="+binderInfo);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
            myBinder=null;

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clean();
    }

    private void clean() {
        if(BindServiceTest.isAlive==true){
            stopService(new Intent(context,BindServiceTest.class));
        }
        if(myBinder!=null){
            unbindService(connectionn);
        }
    }
}
