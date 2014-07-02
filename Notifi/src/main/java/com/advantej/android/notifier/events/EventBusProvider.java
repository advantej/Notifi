package com.advantej.android.notifier.events;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by advantej on 4/2/14.
 */
public class EventBusProvider
{
    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);

    public static Bus getInstance()
    {
        return BUS;
    }

    private EventBusProvider()
    {
        // No instances.
    }
}
