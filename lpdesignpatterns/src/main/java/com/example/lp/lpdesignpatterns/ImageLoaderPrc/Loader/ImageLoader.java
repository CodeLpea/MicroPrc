package com.example.lp.lpdesignpatterns.ImageLoaderPrc.Loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
* 图片加载工具
* */
public class ImageLoader {
    private static final String TAG="ImageLoader";

    /*图片缓存*/
    private ImageCache imageCache=new MemoryCache();
    //线程池，线程数量为cpu的数量
    ExecutorService mExecutorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //注入缓存实现的方式
    public void setImageCache(ImageCache cache){
        imageCache=cache;
    }
    //用于切换到主线程显示ui
    private  Handler mHandler = new Handler(Looper.getMainLooper());
    /*
    *将网络图片加载到指定的imageView控件上
    * */
    public void displayImage(String imageUrl, ImageView imageView){
        Log.i(TAG, "displayImage: ");

        //先查看缓存中是否有该图片
        Bitmap bitmap=imageCache.get(imageUrl);
        //如果有
        if(bitmap!=null){
            Log.i(TAG, "有缓存: ");
            imageView.setImageBitmap(bitmap);
            return;
        }

        //如果没有就提交到线程池，进行下载
        submitLoadRequest(imageUrl,imageView);

    }

    private void submitLoadRequest(final String imageUrl, final ImageView imageView){
        Log.i(TAG, "进行下载: ");
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap=downloadImage(imageUrl);
                if(bitmap==null){
                    return;
                }if(imageView.getTag().equals(imageUrl)){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
                //加入缓存
                imageCache.put(imageUrl,bitmap);
            }
        });

    }

    private Bitmap downloadImage(String imageUrl) {
        Log.i(TAG, "下载中: ");
        Bitmap bitmap=null;
        InputStream in = null;
        try {
            URL url=new URL(imageUrl);
            final HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.connect();
            in=connection.getInputStream();
            bitmap= BitmapFactory.decodeStream(in);
            connection.disconnect();
        }catch (Exception e){
            e.printStackTrace();

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return bitmap;
    }


}
