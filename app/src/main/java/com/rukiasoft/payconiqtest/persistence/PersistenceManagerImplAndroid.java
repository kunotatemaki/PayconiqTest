package com.rukiasoft.payconiqtest.persistence;

import com.rukiasoft.payconiqtest.PayconiqApplication;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;

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
    public void insertUser(User user) {
        mApplication.getmDatabase().userDao().insertAll(user);
    }

    @Override
    public void insertListOfRepos(List<Repo> repos) {
        mApplication.getmDatabase().repoDao().insertAll(repos.toArray(new Repo[repos.size()]));
    }
}
