package com.example.lp.lpdesignpatterns.ImageLoaderPrc.Loader;

import android.widget.ImageView;

import com.example.lp.lpdesignpatterns.ImageLoaderPrc.Utils.Md5Utils;

import java.lang.ref.SoftReference;

/**
 * Image的构建类
 * 不可缺少的配置带有默认值
 * */
public class ImageBuilder {
    //软引用图片加载控件
    private SoftReference<ImageView> imageViewSoftReference;
    private String url;
    private String Md5;
    private int icon;
    private ImageCache imageCache;
    public ImageView getImageView() {
        return imageViewSoftReference.get();
    }
    public String getUrl() {
        return url;
    }

    public String getMd5() {
        return Md5;
    }

    public int getIcon() {
        return icon;
    }

    public ImageCache getImageCache() {
        return imageCache;
    }

    public ImageBuilder setUrl(String url){
        this.url=url;
        this.Md5= Md5Utils.getMD5String(url)+".jpg";
        return this;
    }
    public ImageBuilder setIcon(int icon){
        this.icon=icon;
        return this;
    }
    public ImageBuilder setImageCache(ImageCache imageCache){
        this.imageCache=imageCache;
        return this;
    }
    public ImageBuilder setImageView(ImageView imageView){
        this.imageViewSoftReference=new SoftReference<>(imageView);
        return this;
    }

    public ImageLoader build(){
        ImageLoader.getInstace().excute(this);
        return ImageLoader.getInstace();
    }

}
