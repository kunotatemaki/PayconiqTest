package com.rukiasoft.payconiqtest.model.livedata;

import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposLiveDataImplAndroid<T> extends MutableLiveData<T> implements ReposLivedata<T> {

    @Inject
    public ReposLiveDataImplAndroid() {
    }

    @Override
    public void setLivedataValue(T value) {
        this.setValue(value);
    }

    @Override
    public T getLivedataValue() {
        return this.getValue();
    }
}
