package com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces;

import com.rukiasoft.payconiqtest.model.User;

/**
 * Created by Roll on 10/8/17.
 */

public interface ReposView {

    /***
     * set the Repos' owner in the view
     * @param user: param with information of the user
     */
    void setUserInView(User user);
}
