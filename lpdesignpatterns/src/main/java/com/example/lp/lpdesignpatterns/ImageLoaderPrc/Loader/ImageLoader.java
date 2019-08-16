package com.example.lp.lpdesignpatterns.ImageLoaderPrc.Loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.example.lp.lpdesignpatterns.ImageLoaderPrc.Utils.Md5Utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.lp.lpdesignpatterns.ImageLoaderPrc.Loader.DiskCache.cacheDir;
import static com.example.lp.lpdesignpatterns.ImageLoaderPrc.Utils.FlieUtils.checkFile;


/**
 * 图片加载工具
 */
//目前问题：还不能并发请求
public class ImageLoader {
    private static final String TAG = "ImageLoader";
    private ImageView imageView;
    private String url;
    private String Md5;
    private int icon;
    /*全局唯一缓存变量*/
    private ImageCache imageCache=new MemoryCache();
    //用于切换到主线程显示ui
    private Handler mHandler = new Handler(Looper.getMainLooper());
    //线程池，线程数量为cpu的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /*设置配置*/
    private void checkConfig(ImageBuilder imageBuilder) {
        this.imageView=imageBuilder.getImageView();
        this.url = imageBuilder.getUrl();
        this.Md5 = imageBuilder.getMd5();
        this.icon = imageBuilder.getIcon();
        if (imageBuilder.getImageCache()!=null) {
            this.imageCache = imageBuilder.getImageCache();
        }
    }

    public void excute(ImageBuilder imageBuilder) {
        checkConfig(imageBuilder);
        /*判断是否有缓存*/
        Bitmap bitmap = checkCache(Md5);
        //如果有
        if (bitmap != null) {
            Log.i(TAG, "有缓存: ");
            imageBuilder.getImageView().setImageBitmap(bitmap);
            return;
        }
        /*设置占位图片*/
        showImage(null,icon);
        //如果没有就提交到线程池，进行下载
        submitLoadRequest();
    }


    private void submitLoadRequest() {
        Log.i(TAG, "进行下载: ");
        imageView.setTag(Md5);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                //展示图片
                showImage(bitmap,0);

                //加入缓存Md5Utils.getMD5String(url);
                imageCache.put(Md5, bitmap);
            }
        });
    }

    private Bitmap checkCache(String md5) {
        //先查看缓存中是否有该图片
        Bitmap bitmap = imageCache.get(md5);
        return bitmap;
    }

    /*静态内部类的单例*/
    private static class InstaceImage {
        private static ImageLoader instance = new ImageLoader();
    }

    public static ImageLoader getInstace() {
        return InstaceImage.instance;
    }

    private ImageLoader() {
        checkFile(cacheDir);
    }


    private void showImage(final Bitmap bitmap,final int icon){
        if(bitmap!=null){
            if (imageView.getTag().equals(Md5)) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }else if(icon!=0) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(icon);
                }
            });
        }
    }
    private Bitmap downloadImage(String imageUrl) {
        Log.i(TAG, "下载中: ");
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            in = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
            connection.disconnect();
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
        return bitmap;
    }


}
