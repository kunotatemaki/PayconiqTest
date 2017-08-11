package com.rukiasoft.payconiqtest.repolist.ui.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.User;
import com.rukiasoft.payconiqtest.model.livedata.ReposLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.livedata.CustomLivedata;
import com.rukiasoft.payconiqtest.model.livedata.StatusLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.livedata.UserLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import java.util.List;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposViewmodel extends ViewModel {


    public int lastPageRequested = 0;

    private CustomLivedata<PayconiqConstants.StatusResponse> status;

    private CustomLivedata<List<Repo>> repos;

    private CustomLivedata<User> user;

    public CustomLivedata<List<Repo>> getRepos() {
        if(repos == null){
            repos = new ReposLiveDataImplAndroid();
        }
        return repos;
    }

    public CustomLivedata<User> getUser() {
        if(user == null){
            user = new UserLiveDataImplAndroid();
        }
        return user;
    }

    public CustomLivedata<PayconiqConstants.StatusResponse> getStatus() {
        if(status == null){
            status = new StatusLiveDataImplAndroid();
            status.setLivedataValue(PayconiqConstants.StatusResponse.ORIGINAL_STATE);
        }
        return status;
    }
}
