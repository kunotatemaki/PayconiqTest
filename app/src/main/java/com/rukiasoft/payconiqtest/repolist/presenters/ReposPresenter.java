package com.rukiasoft.payconiqtest.repolist.presenters;

/**
 * Created by Roll on 10/8/17.
 */

public interface ReposPresenter {

    /***
     * load users from cache, network (if available) or from local database
     */
    void loadRepos();

    void getNextBatch();

    void getNextBatchFromNetwork();

    void getNextBacthFromLocalDb();


}
