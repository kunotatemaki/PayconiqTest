package com.rukiasoft.payconiqtest.network;

import com.rukiasoft.payconiqtest.model.CustomLivedata;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import java.util.List;

/**
 * Created by Roll on 10/8/17.
 */

public interface NetworkManager {

    boolean isNetworkAvailable();

    void getDataFromGithub(int page,
                           CustomLivedata<PayconiqConstants.STATUS_RESPONSE> status,
                           CustomLivedata<User> user,
                           CustomLivedata<List<Repo>> repos
    );
}
