package com.rukiasoft.payconiqtest.model.livedata;

import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;

/**
 * Created by Roll on 10/8/17.
 */

public interface ReposLivedata<T> {

    void setLivedataValue(T value);

    T getLivedataValue();

    void addObserverToLivedata(ReposView view, LivedataObserver observer);
}
