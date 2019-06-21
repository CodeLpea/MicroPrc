package com.example.lp.lpautosize.AutoSizeUtils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
/**
 * Desity
 * 今日头条适配方案
 * 简单，低成本
 * */
public class Desity {
    private  static  final float WIDTH=360;//参考设备的宽，单位是dp

    private  static float appDesity=0;//表示屏幕密度

    private  static float appScaleDensity=0;//字体缩放比例，默认为appDesity

   //直接使用在BaseActivity中
    public static void setAppDesity(final Application application, final Activity activity){
        //获取到当前app的屏幕信息
        DisplayMetrics displayMetrics=application.getResources().getDisplayMetrics();
        if(appDesity==0){//初始化操作
            appDesity=displayMetrics.density;
            appScaleDensity=displayMetrics.scaledDensity;

            //添加字体变化回调
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig!=null&&newConfig.fontScale>0){
                        //字体变化，重新赋值，下次启动的时候就会改变
                        appScaleDensity=application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });


        }

        float targetDensity=displayMetrics.widthPixels/WIDTH;//比如适配的设备是1080，那就是1080/360，目标desity=3；


        float targetScaleDensity=targetDensity*(appScaleDensity/appDesity);//目标字体缩放比例

        int targetDensityDpi=(int)(targetDensity*160);//目标DPi

        //替换Activity的density，scaleDensity，desityDpi

        DisplayMetrics dm=activity.getResources().getDisplayMetrics();
        dm.density=targetDensity;
        dm.scaledDensity=targetScaleDensity;
        dm.densityDpi=targetDensityDpi;


    }


}
