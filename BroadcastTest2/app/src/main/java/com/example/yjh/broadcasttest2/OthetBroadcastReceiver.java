package com.example.yjh.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OthetBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "从另一个广播接受到", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
