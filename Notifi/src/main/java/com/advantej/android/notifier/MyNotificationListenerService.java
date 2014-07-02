package com.advantej.android.notifier;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.advantej.android.notifier.content.NotifiDb;
import com.advantej.android.notifier.events.Event;
import com.advantej.android.notifier.events.EventBusProvider;
import com.advantej.android.notifier.model.StandardModel;
import com.legendroids.android.libs.bazzinga.content.BaseDb;

public class MyNotificationListenerService extends NotificationListenerService
{
    private static final String TAG = "MyNotificationListenerService";

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }


    private void initExistingSTN()
    {
        //Throwing a NPE :(
        StatusBarNotification[] statusBarNotifications = getActiveNotifications();

        if (statusBarNotifications != null)
        {
            for (StatusBarNotification sbn : statusBarNotifications)
            {
                extractAndAddToDB(sbn);
            }
        }
    }

    private void extractAndAddToDB(StatusBarNotification sbn)
    {
        Notification notification = sbn.getNotification();
        PendingIntent contentIntent = notification.contentIntent;

        String title = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Bundle extras = notification.extras;
            if (extras != null)
            {
                title = extras.getString(Notification.EXTRA_TITLE);
            }
        }

        String pkg = contentIntent.getCreatorPackage();
        String text1 = notification.tickerText == null ? "" : notification.tickerText.toString();
        String sbnId = String.valueOf(sbn.getId());
        String name = null;

        PackageManager packageManager = getPackageManager();
        try
        {
            ApplicationInfo appInfo = packageManager.getApplicationInfo(pkg, 0);
            name = String.valueOf(packageManager.getApplicationLabel(appInfo));
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        com.advantej.android.notifier.model.Notification myNotification = new com.advantej.android.notifier.model.Notification(pkg, text1, null);
        myNotification.setSbnId(sbnId);
        myNotification.setApplicationName(name);
        myNotification.setIsClearable(sbn.isClearable());
        myNotification.setIsOngoing(sbn.isOngoing());
        myNotification.setPostTime(sbn.getPostTime());
        myNotification.setTag(sbn.getTag());

        if (title != null)
            myNotification.setText2(title);

        StandardModel.saveToDB(this, BaseDb.getDb(), myNotification, com.advantej.android.notifier.model.Notification.class);
        NotifiApplication.sMemNotifications.addPendingIntentForPackage(pkg, sbnId, notification.contentIntent);

        Log.d(TAG, "Notification saved : " + myNotification);

        EventBusProvider.getInstance().post(Event.getEvent(100));

        //This may be done if we are still in memory: TODO maintain in memory, last N pending intents
//        try
//        {
//            contentIntent.send();
//        } catch (PendingIntent.CanceledException e)
//        {
//            e.printStackTrace();
//        }

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn)
    {
        extractAndAddToDB(sbn);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {
        Notification notification = sbn.getNotification();
        Log.d(TAG, "Removed : " + notification.toString());
    }


}
