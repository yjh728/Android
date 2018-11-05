package com.example.yjh.broadcasttest;

import android.arch.lifecycle.ViewModelProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.yjh.broadcasttest.MY_BROADCAST");
                // android 8.0接受不到自定义广播
                // 原因：android 8.0 对静态广播的使用做了一些限制，使用如下方法就可以接收到自定义广播
                // 第一个参数为包名，第二个参数为自定义广播的路径
                intent.setComponent(new ComponentName("com.example.yjh.broadcasttest",
                        "com.example.yjh.broadcasttest.MyBroadcastReceiver"));
                sendOrderedBroadcast(intent, null);
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Toast.makeText(context, "网络连接正常", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "网络未连接", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
