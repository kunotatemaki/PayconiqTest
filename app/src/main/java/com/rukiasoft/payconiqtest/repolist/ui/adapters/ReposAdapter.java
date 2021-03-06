package com.rukiasoft.payconiqtest.repolist.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rukiasoft.payconiqtest.R;
import com.rukiasoft.payconiqtest.databinding.RepoItemBinding;
import com.rukiasoft.payconiqtest.dependencyinjection.scopes.CustomScopes;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
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

@CustomScopes.ActivityScope
public class ReposAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ReposAdapterMethods{

    private List<Repo> mItems;

    private final static int REPO_CELL = 0;
    private final static int PROGRESS_CELL = 1;
    private boolean progressBarVisible = false;

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
        if(progressBarVisible) {
            return mItems.size() + 1;
        }else{
            return mItems.size();
        }

    }


    @Override
    public int getItemViewType(int position) {
        if (position >= mItems.size()) {
            return PROGRESS_CELL;
        }else{
            return REPO_CELL;
        }
    }

    @Override
    public void addItems(List<Repo> items){
        int positionStart = mItems.size();
        if(items.size() > positionStart){
            mItems.addAll(items.subList(positionStart, items.size()));
        }
        //mItems.addAll(items.subList());
        if(positionStart == 0) {
            notifyDataSetChanged();
        }else{
            this.notifyItemRangeInserted(positionStart, items.size());
        }
    }

    @Override
    public void showProgressBar(){
        progressBarVisible = true;
    }

    @Override
    public void hideProgressBar(){
        progressBarVisible = false;
        notifyItemRemoved(mItems.size());
    }

    @Override
    public boolean isShowingLastCellAsProgressBar() {
        return progressBarVisible;
    }

}