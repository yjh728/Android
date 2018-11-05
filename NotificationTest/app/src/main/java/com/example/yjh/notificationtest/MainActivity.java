package com.example.yjh.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 *  当某个应用希望发送一些提示信息，但该程序又不在前台运行时就可以借助通知来实现
 *  创建通知的步骤：
 *      创建一个NotificationManager对象来对通知进行管理，可以调用Context的getSystemService()方法获取，传入Context.NOTIFICATION_SERVICE
 *      借助support-v4库中提供的NotificationCompat类的Builder构造器来创建Notification对象
 *      通过使用一系列setXxx()方法为通知设置要显示的内容，图标等内容
 *      调用NotificationManager的notify()来让通知显示出来，第一个参数是id，第二个参数是Notification对象
 *  使用PendingIntent来实现通知的点击效果，Intent更倾向于理解执行某个动作，而PendingIntent倾向于在某个适当的时机去执行某个动作
 *  用法：
 *      通过PendingIntent类的静态方法getActivity(), getBroadcast(), getService()方法来获取PendingIntent实例
 *      第一个参数为Context，第二个参数一般传入0即可，第三个参数是一个Intent对象，通过这个对象构建出PendingIntent的意图
 *      第四个参数用于确定PendingIntent的行为，有FLAG_ONE_SHOT，FLAG_NO_CREATE，FLAG_CANCEL_CURRENT和FLAG_UPDATE_CURRENT，通常传入0即可。。
 *      再给NotificationCompat.Builder后连缀一个setContentIntent()方法
 *  通过给NotificationCompat.Builder后连缀一个setAutoCancel(true)方法用力啊取消通知
 *  通过NotificationCompat.Builder的setSound()方法在通知发出时播放一段音频，如setSound(Uri.fromFile(new File("...")))
 *  通过NotificationCompat.Builder的setVibrate()方法用于在通知到来时使手机震动，震动需要声明权限：<uses-permission android:name="android.permission.VIBRATE">
 *  通过NotificationCompat.Builder的setLights()方法实现锁屏状态时LED灯闪烁以提示用户去查看
 *  还可以设置长文字，大图片，以及通知的重要程度。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//                NotificationChannel是android8.0新增的特性，如果App的targetSDKVersion>=26，
//                没有设置channel通知渠道的话，就会导致通知无法展示。
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("1", "channel_name",
                            NotificationManager.IMPORTANCE_HIGH);
                    manager.createNotificationChannel(channel);
                }
                Notification notification = new NotificationCompat.Builder(this, "1")
                        .setContentTitle("这是通知标题")
                        .setContentText("这是通知内容")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
                        .setVibrate(new long[]{0, 1000, 1000, 1000})
                        .setLights(Color.GRAY, 1000, 1000)
                        .setAutoCancel(true)
                        .build();
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
