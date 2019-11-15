package com.example.lp.messenger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lp.R;

public class MessengerClientActivity extends AppCompatActivity {
    private static final String TAG="MessengerClientActivity";
    /*来自Server的Mesenger*/
    private Messenger serverMessnger;
    /*clinetMessenger,用于发送消息给Server端*/
    private Messenger clientmessger =new Messenger(new MyClientHandler());
    public static final int FOROM_CLIENT=101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_client);
        initView();

    }

    private class MyClientHandler extends  Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessengerService.FOROM_SERVICE:
                    Log.e(TAG, "Message: "+msg.getData().getString("reply") );
                break;
            }


        }
    }
    private void initView() {
        findViewById(R.id.btn_messager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_messager){
                    if(serverMessnger==null){
                        Log.i(TAG, "bindService: ");
                        bindService();
                    }else {
                        sendMessgeToServer(serverMessnger);
                    }

                }
            }
        });
    }

    private void bindService(){
        Intent intent=new Intent(this,MessengerService.class);
        //startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

     private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: ");
            /*绑定到服务端的Messenger*/
            serverMessnger=new Messenger(service);
            sendMessgeToServer(serverMessnger);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");

        }
    };

    /*发送消息给Server*/
    private void  sendMessgeToServer(Messenger serverMessnger){
        Message message=Message.obtain(null,FOROM_CLIENT);
        Bundle data = new Bundle();
        data.putString("send", "I am from the client");
        message.setData(data);
        message.replyTo= clientmessger;
        try {
            serverMessnger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(serverMessnger!=null){
            unbindService(connection);
        }

    }
}
