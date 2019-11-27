package com.example.lpskin.skin.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Carl on 2018/3/17 017.
 */

public class SkinUtils {
    public static int[] getResId(Context context, int[] attrs) {
        int[] resIds = new int[attrs.length];
        TypedArray typedArray = context.obtainStyledAttributes(attrs);

        for (int i = 0; i < typedArray.length(); i++) {
            resIds[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
        return resIds;
    }

    public static void copyFilesFromAssets(Context context, String assetsPath, String savePath){
        File localFlie=new File(savePath);
        Log.e("copyFilesFromAssets", localFlie.getAbsolutePath());
        if(localFlie.exists()){
            Log.e("copyFilesFromAssets", "已经存在: ");
            return;
        }
        Log.e("copyFilesFromAssets", "不存在，正在创建: ");
        try {
            String fileNames[] = context.getAssets().list(assetsPath);// 获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {// 如果是目录
                File file = new File(savePath);
                file.mkdirs();// 如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFromAssets(context, assetsPath + "/" + fileName,
                            savePath + "/" + fileName);
                }
            } else {// 如果是文件
                InputStream is = context.getAssets().open(assetsPath);
                FileOutputStream fos = new FileOutputStream(new File(savePath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                    // buffer字节
                    fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
