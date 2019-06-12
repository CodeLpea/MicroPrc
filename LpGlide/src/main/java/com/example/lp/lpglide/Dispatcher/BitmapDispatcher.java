package com.example.lp.lpglide.Dispatcher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.example.lp.lpglide.Request.BitmapRequest;
import com.example.lp.lpglide.Request.RequestListener;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.LinkedBlockingDeque;


public class BitmapDispatcher extends Thread {
    //接收阻塞队列
    private LinkedBlockingDeque<BitmapRequest> requestQueue;

    //用于线程切换
    private Handler handler = new Handler(Looper.getMainLooper());

    public BitmapDispatcher(LinkedBlockingDeque requestQueue) {
        this.requestQueue = requestQueue;
    }


    @Override
    public void run() {
        super.run();
        //进行耗时操作
        //从队列中获取图片请求
        while (!isInterrupted()) {
            try {
                BitmapRequest bitmapRequest = requestQueue.take();

                // 设置占位图片
                showLoadingImage(bitmapRequest);

                // 下载url图片
                Bitmap bitmap = findBitmap(bitmapRequest);

                //将图片展示到imagview上
                showImageView(bitmapRequest, bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void showImageView(final BitmapRequest bitmapRequest, final Bitmap bitmap) {
        if (bitmapRequest.getImageViewSoftReference() != null && bitmap != null && bitmapRequest.getUrlMd5().equals(bitmapRequest.getImageViewSoftReference().getTag())) {
            final ImageView imageView = bitmapRequest.getImageViewSoftReference();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    if (bitmapRequest.getRequestListener() != null) {
                        RequestListener requestListener = bitmapRequest.getRequestListener();
                        requestListener.onSuccess(bitmap);//将图片交给接口，以便用户对获取到的图片进行再处理

                    }
                }
            });
        }

    }

    private Bitmap findBitmap(BitmapRequest bitmapRequest) {
        Bitmap bitmap = downLoadBtmap(bitmapRequest.getUrl());

        return bitmap;
    }

    private Bitmap downLoadBtmap(String Url) {
        Bitmap map = null;
        InputStream in = null;
        try {
            URL url = new URL(Url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
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
        return map;
    }

    private void showLoadingImage(BitmapRequest bitmapRequest) {
        if (bitmapRequest.getResId() > 0 && bitmapRequest.getImageViewSoftReference() != null) {
            final int resid = bitmapRequest.getResId();
            final ImageView imageView = bitmapRequest.getImageViewSoftReference();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(resid);
                }
            });

        }

    }
}
