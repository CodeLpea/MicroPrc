package com.example.lp.lpdesignpatterns.ImageLoaderPrc.Loader;

import android.graphics.Bitmap;
import android.util.LruCache;

/*
* 将照片缓存到内存中
* */
public class MemoryCache implements ImageCache{
    private LruCache<String,Bitmap> mMemeryCache;
    public  MemoryCache(){
        initMemoryCache();
    }
    private void initMemoryCache() {
        /*读取内存大小*/
        final  int maxMemory=(int) (Runtime.getRuntime().maxMemory())/1024;
        /*内存的4分之一作为缓存*/
        final int cacheSize=maxMemory/4;
        mMemeryCache=new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mMemeryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemeryCache.put(url,bitmap);

    }
}
