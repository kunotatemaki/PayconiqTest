package com.rukiasoft.payconiqtest.model.livedata;

import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.hardware.usb.UsbRequest;
import android.support.annotation.Nullable;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.User;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class UserLiveDataImplAndroid extends MutableLiveData<User> implements CustomLivedata<User> {

    @Inject
    public UserLiveDataImplAndroid() {
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
                    observer.handleChangesInObservedUser(user);
                }
            });
        }
    }
}
