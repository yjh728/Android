package com.example.yjh.activitytest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;

public class SecondActivity extends BaseActivity {

    public static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        //该方法用于获得启动该活动的Intent
       /* Intent intent = getIntent();
        //getXxxExtra()方法用于获取传递的数据
        String data = intent.getStringExtra("extra_data");
        Log.d("SecondActivity", data);*/
       Button button = findViewById(R.id.Button_6);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               /*Intent intent = new Intent();
               intent.putExtra("data_return", "Hello FirstActivity");
               setResult(RESULT_OK, intent);
               //由于使用startActivityForResult()方法启动SecondActivity的
               //在SecondActivity被销毁后会回调上一个活动的onActivityResult()方法
               finish();
               Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
               startActivity(intent);*/
               Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
               startActivity(intent);
           }
       });

    }

    public static void actionActivity(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("parma2", data2);
        context.startActivity(intent);
    }
}
