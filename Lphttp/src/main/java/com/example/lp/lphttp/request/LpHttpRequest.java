package com.example.lp.lphttp.request;

import com.example.lp.lphttp.response.CallbackListener;

/**
 * 封装请求接口
 * */
public interface LpHttpRequest {
    void setmUrl(String Url);
    void setData(byte[] data);
    void setListener(CallbackListener callbackListener);
    void execute();

}
