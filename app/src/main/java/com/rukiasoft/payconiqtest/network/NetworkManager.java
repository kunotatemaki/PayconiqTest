package com.rukiasoft.payconiqtest.network;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.User;
import com.rukiasoft.payconiqtest.model.livedata.CustomLivedata;

import java.util.List;

/**
 * Created by Roll on 10/8/17.
 */

public interface NetworkManager {

    boolean isNetworkAvailable();

    void getDataFromGithub(int page, CustomLivedata<User> user, CustomLivedata<List<Repo>> repos);
}
