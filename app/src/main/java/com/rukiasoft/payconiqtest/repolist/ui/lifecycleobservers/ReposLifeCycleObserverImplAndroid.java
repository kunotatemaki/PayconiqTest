package com.rukiasoft.payconiqtest.repolist.ui.lifecycleobservers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.provider.Settings;
import android.util.Log;

import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
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
    void injectViewInPresenter(){
        log.d(this, "onstart del observador");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void removeActivityReferenceFromObserver(){
        log.d(this, "ondestroy del observador");
    }

}
