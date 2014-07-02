package com.advantej.android.notifier;

import android.app.Application;

import com.advantej.android.notifier.content.MemNotifications;
import com.advantej.android.notifier.content.NotifiDb;
import com.advantej.android.notifier.model.Notification;
import com.legendroids.android.libs.bazzinga.Bazzinga;

/**
 * Created by advantej on 4/4/14.
 */
public class NotifiApplication extends Application
{
    public static final String PACKAGE_NAME = "com.advantej.android.notifier";

    public static MemNotifications sMemNotifications;

    @Override
    public void onCreate()
    {
        super.onCreate();

        sMemNotifications = new MemNotifications();

        Class[] modelClasses = new Class[] {Notification.class};
        Bazzinga.init(this, PACKAGE_NAME, modelClasses, NotifiDb.DB_NAME, NotifiDb.DB_VERSION);
    }
}
