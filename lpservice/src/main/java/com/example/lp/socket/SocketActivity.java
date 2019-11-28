package com.example.lp.socket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lp.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.os.Build.HOST;
import static com.example.lp.socket.SocketService.PORT;

public class SocketActivity extends AppCompatActivity {
    private static final String TAG = "SocketActivity";
    private Button btn_create_send;
    private TextView tv_show;
    private ExecutorService mExecutorService = Executors.newCachedThreadPool();
    private String msg = "我是客户端：发送消息";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        startService(new Intent(this, SocketService.class));
        initViews();
    }

    private void initViews() {
        btn_create_send = findViewById(R.id.btn_create_send);
        tv_show = findViewById(R.id.tv_show);

        btn_create_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExecutorService.execute(new connectService());

            }
        });
    }


    private class connectService implements Runnable {
        @Override
        public void run() {//可以考虑在此处添加一个while循环，结合下面的catch语句，实现Socket对象获取失败后的超时重连，直到成功建立Socket连接
            try {

                Log.i(TAG, "创建客户端socket: ");
                Socket socket = new Socket("localhost", PORT);      //步骤一
                //socket.setSoTimeout(60000);
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(   //步骤二
                        socket.getOutputStream(), "UTF-8")), true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                sendMsg(printWriter);
                receiveMsg(printWriter, bufferedReader);
            } catch (Exception e) {
                Log.e(TAG, ("connectService:" + e.getMessage()));   //如果Socket对象获取失败，即连接建立失败，会走到这段逻辑
            }
        }
    }

    private void sendMsg(PrintWriter printWriter) {
        Log.i(TAG, "sendMsg: " + printWriter);
        printWriter.println(msg + System.currentTimeMillis());
    }

    private void receiveMsg(PrintWriter printWriter, BufferedReader in) {
        try {
            while (true) {
                final String readLine = in.readLine();//步骤三
                if ((readLine) != null) {
                    Log.e(TAG, "客户端接收:" + readLine);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_show.setText(tv_show.getText() + "\n\r" + readLine + "\n");
                        }
                    });
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendMsg(printWriter);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: " + e);
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, SocketService.class));
    }
}
