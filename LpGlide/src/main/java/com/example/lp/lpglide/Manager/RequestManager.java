package com.example.lp.lpglide.Manager;

import android.util.Log;

import com.example.lp.lpglide.Dispatcher.BitmapDispatcher;
import com.example.lp.lpglide.Request.BitmapRequest;

import java.util.concurrent.LinkedBlockingDeque;

public class RequestManager {
    private static RequestManager requestManager = new RequestManager();

    private RequestManager() {
        start();
    }

    public static RequestManager getRequestManager() {
        return requestManager;
    }

    //创建队列
    private LinkedBlockingDeque<BitmapRequest> requestQueue = new LinkedBlockingDeque<>();

    //Dispather数组
    private BitmapDispatcher[] bitmapDispatchers;

    //将图片请求加入队列中
    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) {
            return;
        }
        if (!requestQueue.contains(bitmapRequest)) {
            requestQueue.add(bitmapRequest);

        }
    }

    public void start() {
        Log.i("RequestManager", "start: ");
        stop();
        startAllDispather();
    }

    //开启所有bitmap加载服务
    private void startAllDispather() {
        //获取当个应用最大的线程数量
        int threadCount = Runtime.getRuntime().availableProcessors();
        Log.i("获取当个应用最大的线程数量", "threadCount: " + threadCount);
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        for (int i = 0; i < threadCount; i++) {
            BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue);
            bitmapDispatcher.start();
            //放入线程组中
            bitmapDispatchers[i] = bitmapDispatcher;

        }
    }

    public void stop() {
        if (bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
                if (!bitmapDispatcher.isInterrupted()) {
                    bitmapDispatcher.interrupt();
                }
            }
        }

    }
}
