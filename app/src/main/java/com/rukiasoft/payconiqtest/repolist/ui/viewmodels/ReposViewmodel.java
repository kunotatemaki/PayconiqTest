package com.rukiasoft.payconiqtest.repolist.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.livedata.ReposLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.livedata.ReposLivedata;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposViewmodel extends ViewModel {

    public int lastPageRequested = 0;

    ReposLivedata<Repo> repos;

    public ReposLivedata<Repo> getRepos() {
        if(repos == null){
            repos = new ReposLiveDataImplAndroid<>();
        }
        return repos;
    }

}
