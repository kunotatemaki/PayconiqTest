package com.rukiasoft.payconiqtest.repolist.presenters.implementations;

import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

@CustomScopes.ActivityScope
public class ReposPresenterImpl implements ReposPresenter{

    private final ReposView mView;

    @Inject
    LoggerHelper log;

    @Inject
    public ReposPresenterImpl(ReposView view) {
        mView = view;
    }

    @Override
    public void loadUsers() {

    }
}
