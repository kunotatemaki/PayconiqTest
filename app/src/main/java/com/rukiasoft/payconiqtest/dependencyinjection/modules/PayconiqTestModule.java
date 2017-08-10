package com.rukiasoft.payconiqtest.dependencyinjection.modules;

import android.app.Application;
import android.content.Context;

import com.rukiasoft.payconiqtest.network.NetworkManager;
import com.rukiasoft.payconiqtest.network.implementations.NetworkManagerImpl;
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

    private final Application application;

    public PayconiqTestModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesContext(){
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    NetworkManager providesNetworkManager(NetworkManagerImpl networkManager){
        return networkManager;
    }

    @Provides
    @Singleton
    LoggerHelper providesLogHelper(AndroidLogHelperImpl logger){
        return logger;
    }

}
