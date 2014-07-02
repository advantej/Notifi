package com.advantej.android.notifier;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import java.util.StringTokenizer;

public class MainActivity extends Activity implements NotificationFragment.OnFragmentInteractionListener
{
    private String PACKAGE_NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        ApplicationInfo applicationInfo = getApplicationInfo();
        PACKAGE_NAME = applicationInfo.packageName;
        String serviceName = PACKAGE_NAME + ".MyNotificationListenerService";

        String compareTo = PACKAGE_NAME + "/" + serviceName;

        boolean anyListeners = false;
        if (str != null)
        {
            StringTokenizer tokenizer = new StringTokenizer(str, ":");
            while (tokenizer.hasMoreTokens())
            {
                String token = tokenizer.nextToken();

                if (token.equals(compareTo))
                {
                    anyListeners = true;
                    break;
                }
            }
        }


        if (! anyListeners)
        {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)  {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id)
    {

    }
}
