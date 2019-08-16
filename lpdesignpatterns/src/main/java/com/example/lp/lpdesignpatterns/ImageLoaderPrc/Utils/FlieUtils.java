package com.example.lp.lpdesignpatterns.ImageLoaderPrc.Utils;

import java.io.File;

public class FlieUtils {
    public static void checkFile(String cacheDir) {
        File file = new File(cacheDir);
        if (!file.exists() && !file.isDirectory()) {
            //此处注意，多级文件夹必须使用mkdirs
            file.mkdirs();
        }
    }
}
