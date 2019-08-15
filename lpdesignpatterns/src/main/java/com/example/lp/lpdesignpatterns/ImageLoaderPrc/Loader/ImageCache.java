package com.example.lp.lpdesignpatterns.ImageLoaderPrc.Loader;

import android.graphics.Bitmap;

/**
 * 图片缓存接口
 * */
public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url,Bitmap bitmap);
}
