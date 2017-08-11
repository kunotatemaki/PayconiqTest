package com.rukiasoft.payconiqtest.model;

import com.rukiasoft.payconiqtest.repolist.ui.mainviews.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;

/**
 * Created by Roll on 10/8/17.
 */

public interface CustomLivedata<T> {

    void forceStorageInLocalDatabaseOnNewData(boolean force);

    void setLivedataValue(T value);

    T getLivedataValue();

    void addObserverToLivedata(ReposView view, LivedataObserver observer);
}
