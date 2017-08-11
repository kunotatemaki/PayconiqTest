package com.rukiasoft.payconiqtest.repolist.ui.livedataobservers;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.User;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import java.util.List;

/**
 * Created by Roll on 11/8/17.
 */

public interface LivedataObserver {

    /***
     * this interface will be implemente by the element who is going to listen to livedata.
     * It has to provide an handle method to be called on the data change callback
     * @param repos
     */
    void handleChangesInObservedRepos(List<Repo> repos);

    void handleChangesInObservedUser(User user);

    void handleChangesInObservedStatus(PayconiqConstants.STATUS_RESPONSE status);

}

