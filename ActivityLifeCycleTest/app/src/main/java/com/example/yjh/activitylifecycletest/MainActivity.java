package com.example.yjh.activitylifecycletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/*
 * MainActivity第一次被创建时会依次执行onCreat(),onStart(),onResume()
 * 当按下第一个按钮时，因被完全遮挡，因此onPause()和onStop()都会执行
 * 当键下返回键时，由于MainActivity已经进入了停止状态，因此执行onReStart()，之后执行onStart()和onResume()
 * 当键下第二个按钮，因MainActivity并未被完全遮挡住，所以只是进入了停止状态，因此只执行onPause()
 * 当键下返回键时，只执行onResume()
 * 当程序退出时，执行onDestory()
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    //活动第一次被创建时调用该方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, tempData);
        }
        Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);
        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key", tempData);
    }

    //在活动由不可见变为可见时调用该方法
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    //调用此方法时的活动一定处于运行状态
    //该方法在活动准备好和用户进行交互时调用
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    //当系统准备去启动或恢复另一活动时调用该方法
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    // 在活动完全不可见时调用该方法
    // 若新活动是一个对话框式活动，则只吊影onPause()方法，不会调用该方法，否则在其之后调用
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    //在活动被销毁之前调用该方法，调用之后活动状态变为销毁状态
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    //活动有停止状态变为运行状态之前调用该方法
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
}
