package com.example.lp.lphttp;

import android.util.Log;

import com.example.lp.lphttp.Task.HttpTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    //单例
    private static ThreadPoolManager threadPoolManager;

    public static ThreadPoolManager getInstance() {
        if (threadPoolManager == null) {
            threadPoolManager = new ThreadPoolManager();
        }
        return threadPoolManager;
    }

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                //拒绝的任务，突然线程池挂掉等情况，在这里处理跑出来的任务，再重新添加到队列。
                addTask(runnable);
            }
        });
        mThreadPoolExecutor.execute(communicateThread);//启动叫号线程
        mThreadPoolExecutor.execute(delayThread);//启动重试线程
    }

    //创建http请求的队列
    private LinkedBlockingDeque<Runnable> mQueue = new LinkedBlockingDeque<>();

    //创建管理队列的线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();


    //创建管理线程池的叫号线程,队列与线程池的交互线程
    public Runnable communicateThread = new Runnable() {
        @Override
        public void run() {
            Runnable runnable = null;
            while (true) {
                try {
                    runnable = mQueue.take();//从队列中不断的去获取异步任务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(runnable);//将异步任务放入线程池中
            }
        }
    };

    public Runnable delayThread = new Runnable() {
        @Override
        public void run() {
            HttpTask Ht = null;
            while (true) {
                try {
                    Ht = mDelayQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Ht.getRetryConut() < 5) {
                    mThreadPoolExecutor.execute(Ht);
                    Ht.setRetryConut(Ht.getRetryConut() + 1);
                    Log.e("===IHttp重试机制", "run: " + Ht.getRetryConut());
                } else {
                    Log.e("===IHttp重试机制", "结束");
                }
            }
        }
    };

    //添加异步任务到队列中
    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                mQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //添加异步任务到队列中
    public void addRetryTask(HttpTask httpTask) {
        if (httpTask != null) {
            httpTask.setDelayTime(5000);//设置延时5000ms
            mDelayQueue.offer(httpTask);

        }
    }
}
