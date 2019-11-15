package com.example.lp.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 *Messeger方式进程通信，服务端
 *
 * */
public class MessengerService extends Service {
    private static final String TAG="MessengerService";
    public static final int FOROM_SERVICE=102;
    private Messenger serverMessenger =new Messenger(new MyServerHandler());
    public MessengerService() {
        Log.i(TAG, "MessengerService: ");
    }

    private class MyServerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessengerClientActivity.FOROM_CLIENT:
                    Log.e(TAG, "handleMessage: "+msg.getData().getString("send"));
                    Messenger clientMessenger = msg.replyTo;
                    sendMessgeToClient(clientMessenger);

                    break;
            }
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
        Log.e(TAG, "当前进程: --------"+android.os.Process.myPid());
        Log.e(TAG, "当前线程: ------------------"+android.os.Process.myTid());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e(TAG, "onStartCommand: ");
        
        return super.onStartCommand(intent, flags, startId);
        
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(TAG, "onBind: ");
        /*将服务端的Messger返回给客户端*/
        return serverMessenger.getBinder();
    }
    /*发送消息给Client*/
    private void  sendMessgeToClient(Messenger serverMessnger){
        Message replyMessage = Message.obtain(null, FOROM_SERVICE);
        Bundle data = new Bundle();
        data.putString("reply", "I am from the server");
        replyMessage.setData(data);
        try {
            serverMessnger.send(replyMessage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
