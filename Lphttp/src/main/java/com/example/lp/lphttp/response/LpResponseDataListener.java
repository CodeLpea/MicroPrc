package com.example.lp.lphttp.response;
/**
 * 将请求得到的结果，返回对应的对象
 * */
public interface LpResponseDataListener<T> {
    void onSuccess(T  clazz);
    void onFailure();

}
