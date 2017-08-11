package com.rukiasoft.payconiqtest.repolist.ui.viewholders;

import android.support.v7.widget.RecyclerView;

import com.rukiasoft.payconiqtest.databinding.RepoItemBinding;
import com.rukiasoft.payconiqtest.model.Repo;

/**
 * Created by Roll on 10/8/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    private RepoItemBinding mBinding;

    public UserViewHolder(RepoItemBinding binding) {

        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(Repo repo){
        mBinding.repoName.setText(repo.getName());
        mBinding.repoDescription.setText(repo.getDescription());
    }

}
