package com.rukiasoft.payconiqtest.repolist.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Repo> mItems;

    @Inject
    ReposPresenter presenter;

    @Inject
    LoggerHelper log;

    @Inject
    public ReposAdapter() {
        this.mItems = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //return items size +1; the last item will be the progress bar
        return mItems.size() +1;

    }

    public void prueba(){
        log.d(this, "estoy en el adapter");
    }

}
