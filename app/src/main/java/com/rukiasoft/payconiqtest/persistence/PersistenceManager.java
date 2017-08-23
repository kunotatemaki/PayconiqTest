package com.rukiasoft.payconiqtest.persistence;

import com.rukiasoft.payconiqtest.model.CustomLivedata;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import java.util.List;

/**
 * Created by Roll on 11/8/17.
 */

public interface PersistenceManager {

    void insertUser(User user);

    void insertListOfRepos(List<Repo> repos);

    void loadUserInfo(String userName, CustomLivedata<User> liveUser);

    void loadReposInfo(String userName, CustomLivedata<List<Repo>> liveRepos,
                       CustomLivedata<PayconiqConstants.STATUS_RESPONSE> status, int page);

}
