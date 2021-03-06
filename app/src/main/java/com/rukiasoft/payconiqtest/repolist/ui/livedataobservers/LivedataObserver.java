package com.rukiasoft.payconiqtest.repolist.ui.livedataobservers;

import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
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
    void handleChangesInObservedRepos(List<Repo> repos, boolean saveInLocalDatabase);

    void handleChangesInObservedUser(User user, boolean saveInLocalDatabase);

    void handleChangesInObservedStatus(PayconiqConstants.STATUS_RESPONSE status);

}

