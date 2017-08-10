package com.rukiasoft.payconiqtest.repolist.presenters;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.livedata.ReposLivedata;

import java.util.List;

/**
 * Created by Roll on 10/8/17.
 */

public interface ReposPresenter {

    /***
     * load users from cache, network (if available) or from local database
     */
    void loadUsers();

    void observeLiveData(ReposLivedata<Repo> repos);


}
