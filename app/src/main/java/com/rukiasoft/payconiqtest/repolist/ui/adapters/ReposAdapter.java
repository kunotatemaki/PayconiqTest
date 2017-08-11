package com.rukiasoft.payconiqtest.repolist.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rukiasoft.payconiqtest.R;
import com.rukiasoft.payconiqtest.databinding.RepoItemBinding;
import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.viewholders.ProgressViewHolder;
import com.rukiasoft.payconiqtest.repolist.ui.viewholders.RepoViewHolder;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Roll on 10/8/17.
 */

public class ReposAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Repo> mItems;

    private final static int REPO_CELL = 0;
    private final static int PROGRESS_CELL = 1;

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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case REPO_CELL:
                RepoItemBinding binding = RepoItemBinding.inflate(inflater);
                holder = new RepoViewHolder(binding);
                break;
            case PROGRESS_CELL:
                View v = inflater.inflate(R.layout.progress_item, parent, false);
                holder = new ProgressViewHolder(v);
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RepoViewHolder){
            ((RepoViewHolder)holder).bind(mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        //return items size +1; the last item will be the progress bar
        return mItems.size() + 1;

    }


    @Override
    public int getItemViewType(int position) {
        if (position >= mItems.size()) {
            return PROGRESS_CELL;
        }else{
            return REPO_CELL;
        }
    }

}