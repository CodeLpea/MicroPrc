package com.example.lp.lphttp.response;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CallbackListenerIml<T> implements CallbackListener {
  private Class<T> responseClass;
  private LpResponseDataListener lpResponseDataListener;
    //用于线程到切换
    private Handler handler = new Handler(Looper.getMainLooper());

  public CallbackListenerIml(Class<T> responseClass,LpResponseDataListener iDataListener){
      this.responseClass=responseClass;
      this.lpResponseDataListener =iDataListener;
  }

    @Override
    public void onSuccess(InputStream inputStream) {
        String response=getContent(inputStream);
        final T clzz= JSON.parseObject(response,responseClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                lpResponseDataListener.onSuccess(clzz);
            }
        });

    }



    @Override
    public void onFailure() {

    }

    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error=" + e.toString());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Error=" + e.toString());
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }



}
