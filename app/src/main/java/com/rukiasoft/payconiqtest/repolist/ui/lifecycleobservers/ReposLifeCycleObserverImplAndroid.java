package com.rukiasoft.payconiqtest.repolist.ui.lifecycleobservers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.mainviews.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 11/8/17.
 */

@CustomScopes.ActivityScope
public class ReposLifeCycleObserverImplAndroid implements ReposLifecycleObserver, LifecycleObserver {

    private final ReposView mView;

    @Inject
    LoggerHelper log;

    @Inject
    ReposPresenter presenter;

    @Inject
    ReposLifeCycleObserverImplAndroid(ReposView view) {
        mView = view;
        //add observer to view in constructor
        mView.addLifecycleObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void actionInOnCreate(){
        log.d(this, "oncreate del observador");

        //force presenter to observe data (repos and user)
        if(presenter instanceof LivedataObserver) {
            mView.getLiveRepos().addObserverToLivedata(mView, (LivedataObserver) presenter);
            mView.getLiveUser().addObserverToLivedata(mView, (LivedataObserver) presenter);
            mView.getLiveStatus().addObserverToLivedata(mView, (LivedataObserver) presenter);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void actionInOnResume(){

        presenter.loadRepos();

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void actionInOnDestroy(){
        log.d(this, "ondestroy del observador");
    }



}
