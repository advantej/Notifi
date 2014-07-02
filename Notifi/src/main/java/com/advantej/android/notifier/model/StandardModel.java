package com.advantej.android.notifier.model;

import android.content.Context;

import com.advantej.android.notifier.content.NotifiDb;
import com.legendroids.android.libs.bazzinga.content.BaseDb;
import com.legendroids.android.libs.bazzinga.model.AbstractBaseModel;

import java.util.List;

/**
 * Created by advantej on 4/4/14.
 */
public class StandardModel extends AbstractBaseModel
{

    public synchronized static <T extends AbstractBaseModel> void saveToDB(Context context, T model, Class<T> modelClass)
    {
        saveToDB(context, BaseDb.getDb(), model, modelClass);
    }

    public synchronized static <T extends AbstractBaseModel> void removeFromDB(Context context, T model, Class<T> modelClass)
    {
        removeFromDB(context, BaseDb.getDb(), model, modelClass);
    }

    public static <T extends AbstractBaseModel> void saveAllToDB(Context context, List<T> models, boolean clear, Class<T> modelClass)
    {
        saveAllToDB(context, BaseDb.getDb(), models, clear, modelClass);
    }

    public static <T extends AbstractBaseModel> T getLocalItemForUniqueId(Context context, String uniqueId, Class<T> modelClass)
    {
        return getLocalItemForUniqueId(context, BaseDb.getDb(), uniqueId, modelClass);
    }
}
