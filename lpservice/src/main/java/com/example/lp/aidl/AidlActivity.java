package com.example.lp.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lp.R;

public class AidlActivity extends AppCompatActivity {
    private static final String TAG="AidlActivity";
    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        initViews();
        toBindService();
    }

    private void initViews() {
        findViewById(R.id.btn_aidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMyAidlInterface!=null){
                    try {
                        String servicesinfo = iMyAidlInterface.getString();
                        String myinfo = "当前进程："+android.os.Process.myPid();
                        Toast.makeText(AidlActivity.this, servicesinfo, Toast.LENGTH_SHORT).show();
                        Toast.makeText(AidlActivity.this,myinfo , Toast.LENGTH_SHORT).show();
                        Log.e(TAG, servicesinfo);
                        Log.e(TAG, myinfo);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void toBindService() {
        bindService(new Intent(this,AidlService.class),serviceConnection,BIND_AUTO_CREATE);
    }
    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /*将ibinder转换为Aidl*/
            iMyAidlInterface=IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidlInterface=null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
