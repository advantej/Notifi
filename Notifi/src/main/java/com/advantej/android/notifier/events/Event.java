package com.advantej.android.notifier.events;

import com.squareup.otto.Produce;

/**
 * Created by advantej on 4/2/14.
 */
public class Event
{
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;

    protected int mCode;
    protected String mMessage;
    protected Object mData;

    public Object getData()
    {
        return mData;
    }

    public void setData(Object data)
    {
        mData = data;
    }


    public String getMessage()
    {
        return mMessage;
    }

    public int getCode()
    {
        return mCode;
    }

    public void setCode(int code)
    {
        mCode = code;
    }

    public void setMessage(String message)
    {
        mMessage = message;
    }

    @Produce
    public static Event getEvent()
    {
        return new Event();
    }

    @Produce
    public static Event getEvent(int code)
    {
        Event event = new Event();
        event.setCode(code);
        return event;
    }

    @Produce
    public static Event getEvent(int code, String message)
    {
        Event event = new Event();
        event.setCode(code);
        event.setMessage(message);
        return event;
    }
}
