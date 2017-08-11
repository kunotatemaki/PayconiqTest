package com.rukiasoft.payconiqtest.repolist.presenters.implementations;

import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.User;
import com.rukiasoft.payconiqtest.model.livedata.CustomLivedata;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

@CustomScopes.ActivityScope
public class ReposPresenterImpl implements ReposPresenter, LivedataObserver{

    private final ReposView mView;

    @Inject
    LoggerHelper log;

    @Inject
    public ReposPresenterImpl(ReposView view) {
        mView = view;
    }


    // region REPOS PRESENTER INTERFACE
    @Override
    public void loadUsers() {

    }

    @Override
    public void observeLiveData(CustomLivedata<Repo> repos) {

    }

    //endregion

    //region LIVEDATA OBSERVER INTERFACE
    @Override
    public void handleChangesInObservedRepos(List<Repo> repos) {
        mView.setReposInView(repos);
    }

    @Override
    public void handleChangesInObservedUser(User user) {
        mView.setUserInView(user);
    }

    //endregion
}
