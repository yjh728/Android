package com.example.yjh.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HelloWorldActivity extends AppCompatActivity {

    private static final String TAG = "HelloWorldActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        // 第一个参数是tag，一般为当前类名，主要用于对打印信息进行过滤
        // 第二个参数是msg，即想打印的具体的内容
        Log.d(TAG, "onCreate: ");
    }
}
