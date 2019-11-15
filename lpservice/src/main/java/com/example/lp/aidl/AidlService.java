package com.example.lp.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlService extends Service {
    public AidlService() {
    }



    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private String myPid(){
        String str="这是AIDL服务进程："+String.valueOf(android.os.Process.myPid());
        Log.e("AidlService", str );
        return str;
    }

    /*新建一个类实现Aidl*/
    class Mybind extends IMyAidlInterface.Stub{
        @Override
        public String getString() throws RemoteException {
            return myPid();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        /*将Aidl的实现类返回*/
        return new Mybind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("AidlActivity", "onDestroy: ");
    }
}
