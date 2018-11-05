package com.example.yjh.uibastpractice;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientThread implements Runnable {

    public static final String TAG = "ClientThread";
    private Socket socket;

    private Handler sendHandler;

    public Handler receiveHandler;

    BufferedReader bufferedReader = null;

    BufferedOutputStream bufferedOutputStream = null;

    public ClientThread(Handler handler) {
        this.sendHandler = handler;
    }


    @Override
    public void run() {
        try {
            socket = new Socket("222.24.34.114", 10086);
            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String content = null;
                    try {
                        while ((content = bufferedReader.readLine()) != null) {
                            Message message = new Message();
                            message.what = 0x123;
                            message.obj = content;
                            sendHandler.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Looper.prepare();
            receiveHandler = new Handler() {
                @Override
                public void handleMessage(Message message) {
                    if (message.what == 0x345) {
                        try {
                            bufferedOutputStream.write((message.obj.toString()+"\r\n").getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (SocketTimeoutException e) {
            System.out.println("网络连接请求超时!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
