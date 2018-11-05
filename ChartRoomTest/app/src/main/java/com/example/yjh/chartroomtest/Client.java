package com.example.yjh.chartroomtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Client extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private Button send;
    private EditText input;
    private Handler handler;
    private MsgAdpater msgAdpater;
    private List<Msg> list = new ArrayList<>();
    private ClientThread clientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        send = findViewById(R.id.send);
        input = findViewById(R.id.input_text);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        msgAdpater = new MsgAdpater(list);
        recyclerView.setAdapter(msgAdpater);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    Msg msg1 = new Msg(Msg.TYPE_RECEIVE, msg.obj.toString());
                    list.add(msg1);
                    msgAdpater.notifyItemInserted(list.size()-1);
                    recyclerView.scrollToPosition(list.size()-1);
                }
            }
        };
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                String s = input.getText().toString();
                if (!s.equals("") && s != null) {
                    Msg msg = new Msg(Msg.TYPE_SEND, s);
                    list.add(msg);
                    Message message = new Message();
                    message.what = 0x345;
                    message.obj = s;
                    clientThread.receiveHandler.sendMessage(message);
                    msgAdpater.notifyItemInserted(list.size() - 1);
                    recyclerView.scrollToPosition(list.size() - 1);
                    input.setText("");
                }
        }
    }
}
