package com.rukiasoft.payconiqtest.repolist.ui.lifecycleobservers;

import android.arch.lifecycle.LifecycleObserver;
import android.databinding.adapters.ViewBindingAdapter;

import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

@CustomScopes.ActivityScope
public class ReposLifecycleObserver implements LifecycleObserver {

    private final ReposView mView;

    @Inject
    LoggerHelper log;

    @Inject
    ReposPresenter presenter;

    @Inject
    ReposLifecycleObserver(ReposView view) {
        mView = view;
        // TODO: 10/8/17 registrar aquí
    }

    public void prueba(){
        log.d(this, "llamo al presenter desde prueba");
    }
}
