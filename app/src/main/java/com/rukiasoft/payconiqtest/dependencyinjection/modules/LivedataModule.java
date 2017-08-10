package com.rukiasoft.payconiqtest.dependencyinjection.modules;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.livedata.ReposLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.livedata.ReposLivedata;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 10/8/17.
 */

@Module
public class LivedataModule {

    @Provides
    @Singleton
    ReposLivedata<Repo> providesReposLiveData(ReposLiveDataImplAndroid<Repo> repos){
        return repos;
    }
}
