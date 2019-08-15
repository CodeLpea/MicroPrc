package com.example.lp.lpdesignpatterns.ImageLoaderPrc.Loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;

import static com.example.lp.lpdesignpatterns.ImageLoaderPrc.Utils.ImageUtils.closeStream;

public class DiskCache implements ImageCache{
    public  static String cacheDir="sdcard/cache/";
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir+url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        putInDisk(url,bitmap);

    }

    private void putInDisk(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(cacheDir+url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream!=null){
                closeStream(fileOutputStream);
            }

        }
    }


}
