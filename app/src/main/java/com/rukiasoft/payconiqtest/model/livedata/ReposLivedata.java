package com.rukiasoft.payconiqtest.model.livedata;

/**
 * Created by Roll on 10/8/17.
 */

public interface ReposLivedata<T> {

    void setLivedataValue(T value);

    T getLivedataValue();

}
