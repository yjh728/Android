package com.example.yjh.runtimepermissiontest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedOutputStream;

/*
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button callPhone = findViewById(R.id.call_phone);
        callPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_phone:
//                判断用户是否已授权，第一个参数为Context，第二个参数为具体的权限名
//                若返回值相等，则说明已经授权
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    该方法用于申请授权，第一个参数是Activity的实例
//                    第二个参数是String[]，将申请的权限名放入数组即可
//                    第三个参数是请求码，只要是唯一值就可以
//                    调用完该方法后，系统会弹出一个权限申请的对话框
//                    由用户选择同意或者拒绝，然后会回调到onRequestPermissionsResult()方法中
//                    授权的结果会封装在grantResults参数中
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    call();
                }
        }
    }

    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10000"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "You Denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
