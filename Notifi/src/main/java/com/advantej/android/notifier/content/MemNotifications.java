package com.advantej.android.notifier.content;

import android.app.PendingIntent;

import com.advantej.android.notifier.model.Notification;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by advantej on 4/7/14.
 */
public class MemNotifications
{
    private Map<String, Map<String, PendingIntent>> mNotificationMap;

    public MemNotifications()
    {
        mNotificationMap = new HashMap<String, Map<String, PendingIntent>>();
    }

    public void addPendingIntentForPackage(String pkg, String notificationId, PendingIntent pendingIntent)
    {
        //TODO check size and pruge if necessary

        Map<String, PendingIntent> pendingIntentMap = mNotificationMap.get(pkg);

        if (pendingIntentMap == null) // first time, need to create
        {
            pendingIntentMap = new HashMap<String, PendingIntent>();
            mNotificationMap.put(pkg, pendingIntentMap);
        }

        pendingIntentMap.put(notificationId, pendingIntent);
    }

    public PendingIntent getPendingIntentForNotification(Notification notification)
    {
        return getPendingIntentForPackage(notification.getPackageName(), notification.getSbnId());
    }

    public PendingIntent getPendingIntentForPackage(String pkg, String notificationId)
    {
        Map<String, PendingIntent> pendingIntentMap = mNotificationMap.get(pkg);

        if (pendingIntentMap == null)
            return null;

        return pendingIntentMap.get(notificationId);
    }
}
