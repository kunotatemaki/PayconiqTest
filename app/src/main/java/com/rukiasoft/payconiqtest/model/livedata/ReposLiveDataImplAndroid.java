package com.rukiasoft.payconiqtest.model.livedata;

import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposLiveDataImplAndroid extends MutableLiveData<List<Repo>> implements ReposLivedata<List<Repo>> {

    @Inject
    public ReposLiveDataImplAndroid() {
    }

    @Override
    public void setLivedataValue(List<Repo> value) {
        this.setValue(value);
    }

    @Override
    public List<Repo> getLivedataValue() {
        return this.getValue();
    }

   @Override
    public void addObserverToLivedata(ReposView view, final LivedataObserver observer) {
        if(view instanceof LifecycleRegistryOwner) {
            this.observe((LifecycleRegistryOwner) view, new Observer<List<Repo>>() {
                @Override
                public void onChanged(@Nullable List<Repo> repos) {
                    observer.handleChangesInObservedRepos(repos);
                }
            });
        }
    }
}
