package com.rukiasoft.payconiqtest.resources;

import android.content.Context;

import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Roll on 11/8/17.
 */

@Singleton
public class ResourcesManagerImplAndroid implements ResourcesManager {

    @Inject
    LoggerHelper log;

    @Inject
    Context context;

    @Inject
    public ResourcesManagerImplAndroid() {
    }

    @Override
    public String getString(int resId) {
        return context.getString(resId);
    }

}
