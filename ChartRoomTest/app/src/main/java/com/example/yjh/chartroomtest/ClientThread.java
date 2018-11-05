package com.example.yjh.chartroomtest;

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
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedOutputStream bufferedOutputStream;
    private Handler sendHandler;
    public Handler receiveHandler;

    public ClientThread(Handler handler) {
        this.sendHandler = handler;
    }


    @Override
    public void run() {
        try {
            socket = new Socket("192.168.43.230", 10086);
            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String s = null;
                        while ((s = bufferedReader.readLine()) != null) {
                            Message message = new Message();
                            message.what = 0x123;
                            message.obj = s;
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
                public void handleMessage(Message msg) {
                    if (msg.what == 0x345) {
                        try {
                            bufferedOutputStream.write(msg.obj.toString().getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (SocketTimeoutException e) {
            System.out.println("网络连接请求超时!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
