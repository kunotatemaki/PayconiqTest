package com.rukiasoft.payconiqtest.dependencyinjection.modules;

import com.rukiasoft.payconiqtest.network.NetworkManager;
import com.rukiasoft.payconiqtest.network.implementations.NetworkManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 10/8/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    NetworkManager providesNetworkManager(NetworkManagerImpl networkManager){
        return networkManager;
    }


}
