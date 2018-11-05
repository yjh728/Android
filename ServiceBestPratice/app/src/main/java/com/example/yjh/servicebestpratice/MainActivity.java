package com.example.yjh.servicebestpratice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.Provider;
import java.sql.Connection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DownloadService.DownloadBindr downloadBindr;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBindr = (DownloadService.DownloadBindr) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startDownload = findViewById(R.id.btn_startDownload);
        Button pauseDownload = findViewById(R.id.btn_pauseDownload);
        Button cancledDownload = findViewById(R.id.btn_cancledDownload);
        startDownload.setOnClickListener(this);
        pauseDownload.setOnClickListener(this);
        cancledDownload.setOnClickListener(this);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startDownload:
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/" +
                        "master/eclipse-inst-win64.exe";
                downloadBindr.startDownload(url);
                break;
            case R.id.btn_pauseDownload:
                downloadBindr.pauseDownload();
                break;
            case R.id.btn_cancledDownload:
                downloadBindr.cancledDownload();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "您禁止了权限，将无法使用程序",
                            Toast.LENGTH_SHORT).show();
                }
        }
    }
}
