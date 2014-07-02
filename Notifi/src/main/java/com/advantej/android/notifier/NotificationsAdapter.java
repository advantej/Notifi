package com.advantej.android.notifier;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.advantej.android.notifier.content.NotifiDb;
import com.advantej.android.notifier.model.Notification;
import com.legendroids.android.libs.bazzinga.content.BaseDb;
import com.legendroids.android.libs.bazzinga.content.ObjectCursor;
import com.legendroids.android.libs.bazzinga.content.ObjectCursorLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by advantej on 4/1/14.
 */
public class NotificationsAdapter extends BaseAdapter implements LoaderManager.LoaderCallbacks<ObjectCursor<Notification>>
{
    private List<Notification> mNotificationList;
    private Context mContext;

    public NotificationsAdapter(Context context)
    {
        mContext = context;
        init();
    }

    public void init()
    {
        if (mNotificationList == null)
        {
            mNotificationList = new ArrayList<Notification>();
        }

        ((Activity) mContext).getLoaderManager().initLoader(100, null, this);
    }


    @Override
    public int getCount()
    {
        return mNotificationList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mNotificationList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.row_notification, null);
        }


        Notification notification = (Notification) getItem(position);

        Drawable appIcon = null;

        try
        {
            appIcon = mContext.getPackageManager().getApplicationIcon(notification.getPackageName());
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        ((TextView)convertView.findViewById(R.id.tv_application_name)).setText(notification.getApplicationName());

        String notiText = notification.getText1();
        TextView textView = (TextView) convertView.findViewById(R.id.tv_notification_text);
        if (notiText == null || notiText.length() == 0)
        {
            textView.setVisibility(View.GONE);
        }
        else
        {
            textView.setVisibility(View.VISIBLE);
            textView.setText(notiText);
        }

        String title = notification.getText2();
        TextView titleView = (TextView) convertView.findViewById(R.id.tv_notification_title);

        if (title == null || title.length() == 0)
        {
            titleView.setVisibility(View.GONE);
        }
        else
        {
            titleView.setVisibility(View.VISIBLE);
            titleView.setText(title);
        }

        if (appIcon != null)
        {
            ((ImageView) convertView.findViewById(R.id.iv_application_icon)).setImageDrawable(appIcon);
        }
        Long postTime = notification.getPostTime();
        if (postTime != null)
        {
            ((TextView)convertView.findViewById(R.id.tv_notification_time)).setText(DateUtils.getRelativeTimeSpanString(postTime));
        }

        View view = convertView.findViewById(R.id.v_indicator);
        PendingIntent intent = NotifiApplication.sMemNotifications.getPendingIntentForNotification(notification);
        if (intent != null)
            view.setBackgroundColor(Color.GREEN);
        else
            view.setBackgroundColor(Color.RED);

        return convertView;
    }

    @Override
    public Loader<ObjectCursor<Notification>> onCreateLoader(int id, Bundle args)
    {
        return new ObjectCursorLoader<Notification>(BaseDb.getDb(), Notification.class, mContext, Notification.DatabaseContract.TABLE_NAME, null, null, null, null, null, Notification.DatabaseContract.DBColumns.COL_POST_TIME + " desc ", null);
    }

    @Override
    public void onLoadFinished(final Loader<ObjectCursor<Notification>> loader, final ObjectCursor<Notification> cursor)
    {
        ((Activity) mContext).runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {

                // Got new data, do something with it.
                if (cursor == null || !cursor.moveToFirst()) {
                    return;
                }

                mNotificationList.clear();
                Notification tmp;
                do
                {
                    tmp = cursor.getModel();
                    mNotificationList.add(tmp);

                }while (cursor.moveToNext());


                notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onLoaderReset(Loader<ObjectCursor<Notification>> loader)
    {

    }
}
