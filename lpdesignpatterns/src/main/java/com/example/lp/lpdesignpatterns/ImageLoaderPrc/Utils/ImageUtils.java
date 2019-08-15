package com.example.lp.lpdesignpatterns.ImageLoaderPrc.Utils;

import java.io.Closeable;
import java.io.IOException;

public class ImageUtils {
    public  static void closeStream(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
