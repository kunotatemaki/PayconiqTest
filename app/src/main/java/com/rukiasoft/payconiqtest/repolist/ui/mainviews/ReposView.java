package com.rukiasoft.payconiqtest.repolist.ui.mainviews;

import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.model.CustomLivedata;
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

    CustomLivedata<PayconiqConstants.STATUS_RESPONSE> getLiveStatus();

    int getLastPageRequested();

    void showProgressBar();

    void hideProgressBar();

    void setLastPageRequested(int page);

    void showMessage(String msg);

    void listenToScrollEvents(boolean listen);

}
