package com.example.lp.lphttp.Task;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.example.lp.lphttp.LpHttp.ThreadPoolManager;
import com.example.lp.lphttp.request.LpHttpRequest;
import com.example.lp.lphttp.response.CallbackListener;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class HttpTask<T> implements Runnable,Delayed {
    private LpHttpRequest lpHttpRequest;
    private long delayTime;
    private int retryConut;

    public HttpTask(T requestData, String url, LpHttpRequest iHttpRequest, CallbackListener callbackListener){
        this.lpHttpRequest =iHttpRequest;
        lpHttpRequest.setmUrl(url);
        lpHttpRequest.setListener(callbackListener);
        String data= JSON.toJSONString(requestData);
        try {
            lpHttpRequest.setData(data.getBytes("utf-8"));//使用阿里的fastJson将泛型转换为byte[]
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try{
            lpHttpRequest.execute();
        }catch (Exception e){
            //将失败的任务放入重试队列中
            ThreadPoolManager.getInstance().addRetryTask(this);
        }


    }

    @Override
    public long getDelay(@NonNull TimeUnit timeUnit) {
        //毫秒为单位
        return timeUnit.convert(this.delayTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed delayed) {
        return 0;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        //毫秒
        this.delayTime = System.currentTimeMillis()+delayTime;
    }

    public int getRetryConut() {
        return retryConut;
    }

    public void setRetryConut(int retryConut) {
        this.retryConut = retryConut;
    }
}
