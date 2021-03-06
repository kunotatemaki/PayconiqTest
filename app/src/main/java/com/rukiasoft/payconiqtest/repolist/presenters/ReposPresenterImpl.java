package com.rukiasoft.payconiqtest.repolist.presenters;

import android.support.annotation.VisibleForTesting;

import com.rukiasoft.payconiqtest.R;
import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.network.NetworkManager;
import com.rukiasoft.payconiqtest.persistence.PersistenceManager;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;
import com.rukiasoft.payconiqtest.repolist.ui.mainviews.ReposView;
import com.rukiasoft.payconiqtest.resources.ResourcesManager;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;
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
    NetworkManager network;

    @Inject
    ResourcesManager resourcesManager;

    @Inject
    PersistenceManager mPersistenceManager;

    @Inject
    public ReposPresenterImpl(ReposView view) {
        mView = view;
    }

    @VisibleForTesting
    public ReposPresenterImpl(ReposView view, LoggerHelper log, NetworkManager network, ResourcesManager resourcesManager, PersistenceManager persistenceManager) {
        this.mView = view;
        this.log = log;
        this.network = network;
        this.resourcesManager = resourcesManager;
        this.mPersistenceManager = persistenceManager;
    }


    // region REPOS PRESENTER INTERFACE
    @Override
    public void loadRepos() {
        //check if there is data in cache
        List<Repo> repos = mView.getLiveRepos().getLivedataValue();
        User user = mView.getLiveUser().getLivedataValue();
        if(user != null && repos != null && !repos.isEmpty()){
            //cached data
            mView.listenToScrollEvents(true);
            mView.setUserInView(user);
            mView.setReposInView(repos);
        }else {
            //allow scroll listener before
            mView.listenToScrollEvents(true);
            getNextBatch();
        }
    }

    @Override
    public void getNextBatch() {
        if(network.isNetworkAvailable()){
            //internet connection, download!!
            getNextBatchFromNetwork();
        }else{
            //load data from local database
            getNextBacthFromLocalDb();
        }
    }

    @Override
    public void getNextBatchFromNetwork() {
        if(mView.getLastPageRequested() < 0){
            // no more pages to download
            return;
        }
        //show progress bar
        mView.showProgressBar();
        //call network endpoint
        network.getDataFromGithub(
                mView.getLastPageRequested() + 1,
                mView.getLiveStatus(),
                mView.getLiveUser(),
                mView.getLiveRepos(),
                PayconiqConstants.NICKNAME
        );
    }

    @Override
    public void getNextBacthFromLocalDb() {

        if(mView.getLastPageRequested() < 0){
            // no more pages to download
            return;
        }else if(mView.getLastPageRequested() == 0) {
            mPersistenceManager.loadUserInfo(PayconiqConstants.NICKNAME, mView.getLiveUser());
        }
        //show progress bar
        mView.showProgressBar();
        mPersistenceManager.loadReposInfo(PayconiqConstants.NICKNAME, mView.getLiveRepos(),
                mView.getLiveStatus(), mView.getLastPageRequested());
    }

    //endregion

    //region LIVEDATA OBSERVER INTERFACE
    @Override
    public void handleChangesInObservedRepos(List<Repo> repos, boolean saveInLocalDatabase) {
        //hide progress bar
        mView.hideProgressBar();
        //set repos
        mView.setReposInView(repos);
        if(saveInLocalDatabase) {
            //store data in local db
            mPersistenceManager.insertListOfRepos(repos);
        }

    }

    @Override
    public void handleChangesInObservedUser(User user, boolean saveInLocalDatabase) {
        mView.setUserInView(user);
        if(saveInLocalDatabase) {
            //store data in local db
            mPersistenceManager.insertUser(user);
        }
    }

    @Override
    public void handleChangesInObservedStatus(PayconiqConstants.STATUS_RESPONSE status) {
        log.d(this, "he cambiado el status a " + status);
        switch (status){
            case ORIGINAL_STATE:
                break;
            case LOAD_FAILED:
                mView.hideProgressBar();
                break;
            case LOAD_OK:
                mView.setLastPageRequested(mView.getLastPageRequested() + 1);
                break;
            case NO_MORE_REPOS:
                mView.setLastPageRequested(PayconiqConstants.STATUS_RESPONSE.NO_MORE_REPOS.getNumVal());
                mView.showMessage(resourcesManager.getString(R.string.no_more_repos));
                break;
        }
    }

    //endregion
}
