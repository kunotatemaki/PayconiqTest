package com.rukiasoft.payconiqtest.dependencyinjection.modules;

import android.app.Application;
import android.content.Context;

import com.rukiasoft.payconiqtest.PayconiqApplication;
import com.rukiasoft.payconiqtest.network.NetworkManager;
import com.rukiasoft.payconiqtest.network.NetworkManagerImpl;
import com.rukiasoft.payconiqtest.persistence.PersistenceManager;
import com.rukiasoft.payconiqtest.persistence.PersistenceManagerImplAndroid;
import com.rukiasoft.payconiqtest.resources.ResourcesManager;
import com.rukiasoft.payconiqtest.resources.ResourcesManagerImplAndroid;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;
import com.rukiasoft.payconiqtest.utils.logger.implementation.AndroidLogHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 10/8/17.
 */

@Module
public class PayconiqTestModule {

    private final PayconiqApplication application;

    public PayconiqTestModule(PayconiqApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    PayconiqApplication providesPayconiqApplication(){
        return application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    LoggerHelper providesLogHelper(AndroidLogHelperImpl logger) {
        return logger;
    }

    @Provides
    @Singleton
    NetworkManager providesNetworkManager(NetworkManagerImpl networkManager) {
        return networkManager;
    }

    @Provides
    @Singleton
    ResourcesManager providesResourcesManager(ResourcesManagerImplAndroid resources){
        return resources;
    }
    @Provides
    @Singleton
    PersistenceManager providesPersistenceManager(PersistenceManagerImplAndroid persistence){
        return persistence;
    }

}
