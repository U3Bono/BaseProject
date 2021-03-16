package com.example.baseproject.utils;

/**
 * 描述：消息栏工具
 */

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.baseproject.R;

public class NotificationUtil extends ContextWrapper {

    private NotificationManager manager;
    public static final String id = "notification";
    public static final String name = "videoUpload";
    private Notification.Builder builder;

    public NotificationUtil(Context context) {
        super(context);
    }

    public void createNotificationChannel() {
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            getManager().createNotificationChannel(channel);
        }

    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public Notification.Builder getChannelNotification(String title, String content) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(getApplicationContext(), id)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setAutoCancel(true);
            return builder;
        } else {
            return null;
        }
    }

    public NotificationCompat.Builder getNotification_25(String title, String content) {
        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    public void sendNotification(String title, String content) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            Notification notification = getChannelNotification
                    (title, content).setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(
                            getResources(), R.mipmap.ic_launcher))
                    .setOnlyAlertOnce(true)
                    .setOngoing(true).build();
            getManager().notify(1, notification);
        } else {
            Notification notification = getNotification_25(title, content).build();
            getManager().notify(1, notification);
        }
    }

    public void uploadProgress(int progress) {
        builder.setContentText(progress + "%");
        builder.setProgress(100, progress, false);
        getManager().notify(1, builder.build());
    }

    public void setText(String text) {
        builder.setContentText(text);
        getManager().notify(1, builder.build());
        manager.cancel(1);
    }
}
