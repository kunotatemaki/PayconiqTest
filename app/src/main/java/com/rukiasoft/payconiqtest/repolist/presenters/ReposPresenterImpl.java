package com.rukiasoft.payconiqtest.repolist.presenters;

import android.arch.lifecycle.ViewModelProviders;

import com.rukiasoft.payconiqtest.R;
import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.persistence.PersistenceManager;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.network.NetworkManager;
import com.rukiasoft.payconiqtest.repolist.ui.livedataobservers.LivedataObserver;
import com.rukiasoft.payconiqtest.repolist.ui.mainviews.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.viewmodels.ReposViewmodel;
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


    // region REPOS PRESENTER INTERFACE
    @Override
    public void loadRepos() {
        //check if there is data in cache
        List<Repo> repos = mView.getLiveRepos().getLivedataValue();
        User user = mView.getLiveUser().getLivedataValue();
        if(user != null && repos != null && !repos.isEmpty()){
            //cached data
            mView.setUserInView(user);
            mView.setReposInView(repos);
        }else if(network.isNetworkAvailable()){
            //internet connection, download!!
            //allow scroll listener before
            mView.listenToScrollEvents(true);
            getNextBatchFromNetwork();
        }else{
            //load data from local database
            mView.listenToScrollEvents(false);
            loadDataFromLocalDatabse(PayconiqConstants.NICKNAME);

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
                mView.getLiveRepos()
        );
    }

    @Override
    public void loadDataFromLocalDatabse(String ownerName) {
        mPersistenceManager.loadUserInfo(ownerName, mView.getLiveUser());
        mPersistenceManager.loadReposInfo(ownerName, mView.getLiveRepos());
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
        // TODO: 11/8/17 manejar la respuesta del retrofit (subir el número de páginas, mostrar mensaje de error...)
        log.d(this, "he cambiado el status a " + status);
        switch (status){
            case ORIGINAL_STATE:
                break;
            case DOWNLOAD_FAILED:
                mView.hideProgressBar();
                break;
            case DOWNLOAD_OK:
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
