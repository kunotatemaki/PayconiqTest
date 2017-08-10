package com.rukiasoft.payconiqtest.repolist.ui.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.livedata.ReposLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.livedata.ReposLivedata;

import java.util.List;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposViewmodel extends ViewModel {

    public int lastPageRequested = 0;

    ReposLivedata<List<Repo>> repos;

    public ReposLivedata<List<Repo>> getRepos() {
        if(repos == null){
            repos = new ReposLiveDataImplAndroid();
        }
        return repos;
    }

}
