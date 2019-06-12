package com.example.lp.lpglide.Request;

import android.content.Context;
import android.widget.ImageView;

import com.example.lp.lpglide.Manager.RequestManager;
import com.example.lp.lpglide.Utils.Md5Utils;

import java.lang.ref.SoftReference;

/**
 * 图片请求类
 * */
public class BitmapRequest {
    //图片请求的地址
    private String url;

    //占位图片
    private int resId;

    //软引用图片加载控件
    private SoftReference<ImageView> imageViewSoftReference;

    //回调对象
    private RequestListener requestListener;

    //图片标志校验
    private String urlMd5;


    private Context context;

    public BitmapRequest(Context context) {
        this.context = context;
    }

    //------提供对外的链式传入参数的方法

    public BitmapRequest load(String url){
        this.url=url;
        this.urlMd5= Md5Utils.getMD5String(url);
        return this;
    }

    public BitmapRequest loading(int resId){
        this.resId=resId;
        return this;
    }
    public BitmapRequest listener(RequestListener listener){
        this.requestListener=listener;
        return this;
    }

    public void Into(ImageView imageView){
        imageView.setTag(this.urlMd5);
        this.imageViewSoftReference=new SoftReference<>(imageView);

        //将图片请求添加到队列中
        RequestManager.getRequestManager().addBitmapRequest(this);


    }


    public String getUrl() {
        return url;
    }

    public int getResId() {
        return resId;
    }

    public ImageView getImageViewSoftReference() {
        return imageViewSoftReference.get();
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public String getUrlMd5() {
        return urlMd5;
    }
}
