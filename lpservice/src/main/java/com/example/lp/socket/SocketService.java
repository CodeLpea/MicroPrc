package com.example.lp.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketService extends Service {
    private static final String TAG = "SocketService";
    public static final int PORT = 8688;


    private List<Socket> clinetSockets = new ArrayList<Socket>();

    private ExecutorService mExecutorService = Executors.newCachedThreadPool();

    private ServerSocket serverSocket = null;

    private String reciveMsg;

    private String sendMsg;

    private String msg="服务端发送信息011110";

    public SocketService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        initService();
    }

    private void initService() {
        try {
            //新建socketService
            serverSocket = new ServerSocket(PORT);
            mExecutorService.execute(new acceptRunnable());
            Log.e(TAG, "Socket服务已经启动 ");


        } catch (Exception e) {
            e.printStackTrace();
       }


    }
    class acceptRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "已经连接: "+socket);
                clinetSockets.add(socket);
                mExecutorService.execute(new ServiceRunnable(socket));
            }
        }
    }



    class ServiceRunnable implements Runnable {
        private Socket socket;
        private BufferedReader in = null;
        private PrintWriter printWriter = null;

        public ServiceRunnable(Socket socket) {                         //这段代码对应步骤三
            this.socket = socket;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(), "UTF-8"));
                printWriter.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            try {
                while (true) {                                   //循环接收、读取 Client 端发送过来的信息
                    if ((reciveMsg = in.readLine()) != null) {
                        System.out.println("receiveMsg:" + reciveMsg);
                        if (reciveMsg.equals("0")) {
                            System.out.println("客户端请求断开连接");
                            printWriter.println("服务端断开连接" + "（服务器发送）");
                            clinetSockets.remove(socket);
                            in.close();
                            socket.close();                         //接受 Client 端的断开连接请求，并关闭 Socket 连接
                            break;
                        } else {
                            Log.e(TAG, "我是服务端: 已接收"+reciveMsg );
                            sendMsg = "我是服务端: 发送消息"+System.currentTimeMillis();
                            printWriter.println(sendMsg);           //向 Client 端反馈、发送信息
                            //sendMsg(socket);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
