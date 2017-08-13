package com.rukiasoft.payconiqtest.repolist.ui.adapters;

import com.rukiasoft.payconiqtest.persistence.entities.Repo;

import java.util.List;

/**
 * Created by Roll on 14/8/17.
 */

public interface ReposAdapterMethods {

    void addItems(List<Repo> items);

    void showProgressBar();

    void hideProgressBar();

    boolean isShowingLastCellAsProgressBar();
}
