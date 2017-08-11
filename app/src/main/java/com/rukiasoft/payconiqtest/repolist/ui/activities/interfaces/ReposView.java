package com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.User;
import com.rukiasoft.payconiqtest.model.livedata.CustomLivedata;
import com.rukiasoft.payconiqtest.repolist.ui.lifecycleobservers.ReposLifecycleObserver;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import java.util.List;

/**
 * Created by Roll on 10/8/17.
 */

public interface ReposView {

    /***
     * set the Repos' owner in the view
     * @param user: param with information of the user
     */
    void setUserInView(User user);

    void setReposInView(List<Repo> repos);

    void addLifecycleObserver(ReposLifecycleObserver observer);

    CustomLivedata<List<Repo>> getLiveRepos();

    CustomLivedata<User> getLiveUser();

    CustomLivedata<PayconiqConstants.StatusResponse> getLiveStatus();


}
