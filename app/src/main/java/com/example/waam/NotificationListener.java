package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationListener extends NotificationListenerService {

    private final String TAG = this.getClass().getSimpleName();
    private final String Notification_Settings = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

    @Override
    public void onCreate() {
        super.onCreate();
        startActivity(new Intent(Notification_Settings));
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.provider.extra.APP_PACKAGE");
        String text = extras.getCharSequence("android.text").toString();
        Log.i(TAG, title + " " + text);
    }

    @Override
    public IBinder onBind(Intent i) {
        return super.onBind(i);
    }
}

