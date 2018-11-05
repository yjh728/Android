package com.example.yjh.playvideotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;

    private Button play;

    private Button pause;

    private Button replay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.vv_video_play);
        play = findViewById(R.id.btn_play);
        pause = findViewById(R.id.btn_pause);
        replay = findViewById(R.id.btn_replay);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPlayer();
        }
    }

    private void initVideoPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "IMG_1659.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPlayer();
                } else {
                    Toast.makeText(MainActivity.this, "您禁止了存储权限!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.btn_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.btn_replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }
}
