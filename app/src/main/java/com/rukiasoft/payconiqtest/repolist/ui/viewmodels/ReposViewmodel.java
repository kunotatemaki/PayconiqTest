package com.rukiasoft.payconiqtest.repolist.ui.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.rukiasoft.payconiqtest.model.CustomLivedata;
import com.rukiasoft.payconiqtest.model.ReposLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.StatusLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.UserLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import java.util.List;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposViewmodel extends ViewModel {

    public boolean userInfoExpanded = true;

    public int lastPageRequested = 0;

    public boolean canListenScrollEvents = true;

    private CustomLivedata<PayconiqConstants.STATUS_RESPONSE> status;

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

    public CustomLivedata<PayconiqConstants.STATUS_RESPONSE> getStatus() {
        if(status == null){
            status = new StatusLiveDataImplAndroid();
            status.setLivedataValue(PayconiqConstants.STATUS_RESPONSE.ORIGINAL_STATE);
        }
        return status;
    }
}
