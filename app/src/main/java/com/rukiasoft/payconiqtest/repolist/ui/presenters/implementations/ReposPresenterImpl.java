package com.rukiasoft.payconiqtest.repolist.ui.presenters.implementations;

import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.presenters.ReposPresenter;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposPresenterImpl implements ReposPresenter{

    private final ReposView mView;
    @Inject
    public ReposPresenterImpl(ReposView view) {
        mView = view;
    }

}
