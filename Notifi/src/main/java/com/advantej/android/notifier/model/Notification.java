package com.advantej.android.notifier.model;

import com.legendroids.android.libs.bazzinga.model.AbstractBaseModel;
import com.legendroids.android.libs.bazzinga.model.Props;
import com.legendroids.android.libs.bazzinga.model.TableName;

import java.util.Date;

/**
 * Created by advantej on 3/31/14.
 */

@TableName(name = Notification.DatabaseContract.TABLE_NAME, key_column_name = "_ID")
public class Notification extends AbstractBaseModel
{
    @Props(db_column = DatabaseContract.DBColumns.COL_SBN_ID)
    private String mSbnId;

    @Props(db_column = DatabaseContract.DBColumns.COL_PACKAGE_NAME)
    private String mPackageName;

    @Props(db_column = DatabaseContract.DBColumns.COL_APP_NAME)
    private String mApplicationName;

    @Props(db_column = DatabaseContract.DBColumns.COL_TEXT_1)
    private String mText1;

    @Props(db_column = DatabaseContract.DBColumns.COL_TEXT_2)
    private String mText2;

    @Props(db_column = DatabaseContract.DBColumns.COL_TAG)
    private String mTag;

    @Props(db_column = DatabaseContract.DBColumns.COL_POST_TIME)
    private Long mPostTime;

    @Props(db_column = DatabaseContract.DBColumns.COL_IS_CLEARABLE)
    private Boolean mIsClearable;

    @Props(db_column = DatabaseContract.DBColumns.COL_IS_ON_GOING)
    private Boolean mIsOngoing;

    public static class DatabaseContract
    {
        public static final String TABLE_NAME = "notification";

        public static class DBColumns
        {
            public static final String COL_SBN_ID = "mSbnId";
            public static final String COL_PACKAGE_NAME = "mPackageName";
            public static final String COL_APP_NAME = "mApplicationName";
            public static final String COL_TEXT_1 = "mText1";
            public static final String COL_TEXT_2 = "mText2";
            public static final String COL_TAG = "mTag";
            public static final String COL_POST_TIME = "mPostTime";
            public static final String COL_IS_CLEARABLE = "mIsClearable";
            public static final String COL_IS_ON_GOING = "mIsOngoing";
        }
    }

    public void reset()
    {
        mPackageName = null;
        mApplicationName = null;
        mText1 = null;
        mText2 = null;
        mPostTime = null;
        mIsClearable = null;
        mIsOngoing = null;
    }

    public Notification()
    {
        reset();
    }

    public Notification(String mPackageName, String mText1, String mText2)
    {
        reset();
        this.mPackageName = mPackageName;
        this.mText1 = mText1;
        this.mText2 = mText2;
    }

    public String getSbnId()
    {
        return mSbnId;
    }

    public void setSbnId(String sbnId)
    {
        mSbnId = sbnId;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public void setPackageName(String packageName)
    {
        mPackageName = packageName;
    }

    public String getText1()
    {
        return mText1;
    }

    public void setText1(String text1)
    {
        mText1 = text1;
    }

    public String getText2()
    {
        return mText2;
    }

    public void setText2(String text2)
    {
        mText2 = text2;
    }

    public String getApplicationName()
    {
        return mApplicationName;
    }

    public void setApplicationName(String applicationName)
    {
        mApplicationName = applicationName;
    }

    public Long getPostTime()
    {
        return mPostTime;
    }

    public void setPostTime(Long postTime)
    {
        mPostTime = postTime;
    }

    public Boolean getIsOngoing()
    {
        return mIsOngoing;
    }

    public void setIsOngoing(Boolean isOngoing)
    {
        mIsOngoing = isOngoing;
    }

    public Boolean getIsClearable()
    {
        return mIsClearable;
    }

    public void setIsClearable(Boolean isClearable)
    {
        mIsClearable = isClearable;
    }

    public String getTag()
    {
        return mTag;
    }

    public void setTag(String tag)
    {
        mTag = tag;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Notification ( ");
        stringBuilder.append(" Application : ");
        stringBuilder.append(mApplicationName);
        stringBuilder.append(" Package : ");
        stringBuilder.append(mPackageName);
        stringBuilder.append(" Text 1 : ");
        stringBuilder.append(mText1);
        stringBuilder.append(" Text 2 : ");
        stringBuilder.append(mText2);
        stringBuilder.append(" PostTime : ");
        stringBuilder.append(mPostTime != null ? new Date(mPostTime) : "null");
        stringBuilder.append(" IsOnGoing : ");
        stringBuilder.append(mIsOngoing != null ? mIsOngoing : "null");
        stringBuilder.append(" IsClearable : ");
        stringBuilder.append(mIsClearable != null ? mIsClearable : "null");
        stringBuilder.append(") ");
        return stringBuilder.toString();
    }
}
