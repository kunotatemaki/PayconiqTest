package com.rukiasoft.payconiqtest.model;

import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.repolist.ui.mainviews.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class UserLiveDataImplAndroid extends MutableLiveData<User> implements CustomLivedata<User> {

    private boolean saveInLocalStorage = false;

    @Inject
    public UserLiveDataImplAndroid() {
    }

    @Override
    public void forceStorageInLocalDatabaseOnNewData(boolean force) {
        saveInLocalStorage = force;
    }

    @Override
    public void setLivedataValue(User value) {
        this.setValue(value);
    }

    @Override
    public User getLivedataValue() {
        return this.getValue();
    }

   @Override
    public void addObserverToLivedata(ReposView view, final LivedataObserver observer) {
        if(view instanceof LifecycleRegistryOwner) {
            this.observe((LifecycleRegistryOwner) view, new Observer<User>() {
                @Override
                public void onChanged(@Nullable User user) {
                    observer.handleChangesInObservedUser(user, saveInLocalStorage);
                }
            });
        }
    }
}
