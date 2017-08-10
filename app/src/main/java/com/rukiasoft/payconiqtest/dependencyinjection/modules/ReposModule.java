package com.rukiasoft.payconiqtest.dependencyinjection.modules;

import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.lifecycleobservers.ReposLifecycleObserver;
import com.rukiasoft.payconiqtest.repolist.ui.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.presenters.implementations.ReposPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 10/8/17.
 */

@Module
public class ReposModule {

    final private ReposView mView;

    public ReposModule(ReposView view) {
        mView = view;
    }

    @Provides
    @CustomScopes.ActivityScope
    ReposPresenter providesReposPresenter(ReposPresenterImpl presenter){
        return presenter;
    }

    @Provides
    @CustomScopes.ActivityScope
    ReposLifecycleObserver providesReposLifecycleObserver(){
        return new ReposLifecycleObserver();
    }

    @Provides
    @CustomScopes.ActivityScope
    ReposView providesReposView(){
        return mView;
    }


}
