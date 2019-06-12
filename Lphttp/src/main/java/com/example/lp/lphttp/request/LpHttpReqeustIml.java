package com.example.lp.lphttp.request;

import android.util.Log;

import com.example.lp.lphttp.response.CallbackListener;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;

/**
 * 实现IHttpRequest
 */
public class LpHttpReqeustIml implements LpHttpRequest {

    private String mUrl;
    private byte[] data;
    private CallbackListener callbackListener;
    private HttpURLConnection urlConnection;

    @Override
    public void setmUrl(String Url) {
        this.mUrl = Url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;

    }

    @Override
    public void setListener(CallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    public void execute() {
        URL url = null;
        Log.i("LpHttpReqeustIml", "=======启动IHttp请求: "+android.os.Process.myTid());
        try {
            url = new URL(this.mUrl);
            urlConnection = (HttpURLConnection) url.openConnection();//打开http连接
            urlConnection.setConnectTimeout(10000);//连接的超时时间
            urlConnection.setUseCaches(false);//不使用缓存
            urlConnection.setInstanceFollowRedirects(true);//成员你函数，设置连接是否可以被重定向
            urlConnection.setReadTimeout(5000);//相应的超时时间
            urlConnection.setDoInput(true);//设置这个连接是否可以写入数据
            urlConnection.setDoOutput(true);//设置这个连接是否可以输出数据
            urlConnection.setRequestMethod("POST");//设置请求的方式
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//设置消息的类型
            urlConnection.connect();// 连接，从上述至此的配置必须要在connect之前完成，实际上它只是建立了一个与服务器的TCP连接
            //-------------使用字节流发送数据--------------
            OutputStream out = urlConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);//缓冲字节流包装字节流
            if (data != null) {
                bos.write(data);
            }
            //把这个字节数组的数据写入缓冲区中
            bos.flush();//刷新缓冲区，发送数据
            out.close();
            bos.close();
            //------------字符流写入数据------------
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {//得到服务端的返回码是否连接成功
                InputStream in = urlConnection.getInputStream();
                callbackListener.onSuccess(in);
            }else {
                throw new RuntimeException("IHttp请求失败") ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("IHttp请求失败") ;
        }
        finally {
            urlConnection.disconnect();//释放资源
        }


    }
}
