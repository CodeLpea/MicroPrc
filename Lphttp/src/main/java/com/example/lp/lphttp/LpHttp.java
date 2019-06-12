package com.example.lp.lphttp;

import com.example.lp.lphttp.Task.HttpTask;
import com.example.lp.lphttp.request.LpHttpReqeustIml;
import com.example.lp.lphttp.request.LpHttpRequest;
import com.example.lp.lphttp.response.CallbackListener;
import com.example.lp.lphttp.response.CallbackListenerIml;
import com.example.lp.lphttp.response.LpResponseDataListener;

/**
 * 提供给应用层的接口
 * */
public class LpHttp {

    public static<T,M> void sendJsonRequest(T requestData,String url,Class<M> response,LpResponseDataListener lpResponseDataListener){
        LpHttpRequest httpRequest=new LpHttpReqeustIml();
        CallbackListener callbackListener=new CallbackListenerIml<>(response,lpResponseDataListener);
        HttpTask httpTask=new HttpTask(requestData,url,httpRequest,callbackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }
}
