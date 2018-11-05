package com.example.yjh.servicebestpratice;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;
import java.security.interfaces.DSAKey;

public class DownloadService extends Service {

    private DownloadTask downloadTask;

    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onPregress(int progress) {
            startForeground(1, getNotificastion("正在下载...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotificastion("Download Success", -1));
            Toast.makeText(DownloadService.this, "下载成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            getNotificationManager().notify(1, getNotificastion("Download Failed", -1));
            Toast.makeText(DownloadService.this, "下载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            if (downloadTask != null && !downloadTask.isCancelled()) {
                downloadTask = null;
                Toast.makeText(DownloadService.this, "下载暂停", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancled() {
            if (downloadTask != null && !downloadTask.isCancelled()) {
                downloadTask = null;
                getNotificationManager().notify(1, getNotificastion("下载关闭", -1));
                Toast.makeText(DownloadService.this, "下载关闭", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private DownloadBindr mBind = new DownloadBindr();

    class DownloadBindr extends Binder{
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotificastion("正在下载", 0));
                Toast.makeText(DownloadService.this, "正在下载", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if(downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancledDownload() {
            if (downloadTask != null) {
                downloadTask.cancledDownload();
            }
            if (downloadUrl != null) {
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory, fileName);
                if (file.exists()) {
                    file.delete();
                }
                getNotificationManager().cancel(1);
                stopForeground(true);
                Toast.makeText(DownloadService.this, "下载关闭", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBind;
    }

    private Notification getNotificastion(String title, int i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "yjh",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1");
        builder.setContentTitle(title);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        if (i >= 0) {
            builder.setContentText(i+"%");
            builder.setProgress(100, i, false);
        }
        return builder.build();
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
}
