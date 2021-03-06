package com.rukiasoft.payconiqtest.persistence;

import android.os.AsyncTask;

import com.rukiasoft.payconiqtest.PayconiqApplication;
import com.rukiasoft.payconiqtest.model.CustomLivedata;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Roll on 11/8/17.
 */

@Singleton
public class PersistenceManagerImplAndroid implements PersistenceManager {

    @Inject
    PayconiqApplication mApplication;

    @Inject
    public PersistenceManagerImplAndroid() {
    }


    @Override
    public void insertUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mApplication.getmDatabase().userDao().insertAll(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {

            }
        }.execute();
    }

    @Override
    public void insertListOfRepos(final List<Repo> repos) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mApplication.getmDatabase().repoDao().insertAll(repos.toArray(new Repo[repos.size()]));
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {

            }
        }.execute();
    }

    @Override
    public void loadUserInfo(final String userName, final CustomLivedata<User> liveUser) {
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... params) {
                return mApplication.getmDatabase().userDao().findByName(userName);
            }

            @Override
            protected void onPostExecute(User user) {
                liveUser.forceStorageInLocalDatabaseOnNewData(false);
                liveUser.setLivedataValue(user);
            }
        }.execute();
    }

    @Override
    public void loadReposInfo(final String userName, final CustomLivedata<List<Repo>> liveRepos,
                              final CustomLivedata<PayconiqConstants.STATUS_RESPONSE> status, final int page) {
        new AsyncTask<Void, Void, List<Repo>>() {
            @Override
            protected List<Repo> doInBackground(Void... params) {
                return mApplication.getmDatabase().repoDao().loadAllByOwnerName(userName,
                        PayconiqConstants.PER_PAGE_VALUE, PayconiqConstants.PER_PAGE_VALUE * page);
            }

            @Override
            protected void onPostExecute(List<Repo> repos) {
                liveRepos.forceStorageInLocalDatabaseOnNewData(false);
                List<Repo> repoList = liveRepos.getLivedataValue();
                if(repoList == null){
                    repoList = new ArrayList<>();
                }
                repoList.addAll(repos);
                liveRepos.setLivedataValue(repoList);
                //update page number with status observable
                if(repos.isEmpty()){
                    status.setLivedataValue(PayconiqConstants.STATUS_RESPONSE.NO_MORE_REPOS);
                }else {
                    status.setLivedataValue(PayconiqConstants.STATUS_RESPONSE.LOAD_OK);
                }
            }
        }.execute();
    }


}
