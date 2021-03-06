package com.rukiasoft.payconiqtest.repolist.ui.mainviews;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rukiasoft.payconiqtest.PayconiqApplication;
import com.rukiasoft.payconiqtest.R;
import com.rukiasoft.payconiqtest.databinding.ActivityReposBinding;
import com.rukiasoft.payconiqtest.dependencyinjection.modules.ReposModule;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.model.CustomLivedata;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.adapters.ReposAdapter;
import com.rukiasoft.payconiqtest.repolist.ui.lifecycleobservers.ReposLifecycleObserver;
import com.rukiasoft.payconiqtest.repolist.ui.listeners.EndlessRecyclerViewScrollListener;
import com.rukiasoft.payconiqtest.repolist.ui.viewmodels.ReposViewmodel;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;
import com.rukiasoft.payconiqtest.utils.ui.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class ReposActivity extends BaseActivity implements ReposView, AppBarLayout.OnOffsetChangedListener{

    @Inject
    ReposPresenter presenter;

    @Inject
    ReposLifecycleObserver observer;

    @Inject
    LoggerHelper log;

    @Inject
    ReposAdapter mAdapter;

    private ActivityReposBinding mBinding;
    private RecyclerView mRecyclerView;
    private EndlessRecyclerViewScrollListener mScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inject dependencies
        ((PayconiqApplication)getApplication())
                .getmComponent()
                .getReposSubcomponent(new ReposModule(this))
                .inject(this);

        //bind views
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_repos);

        log.d(this, "llamo al presenter desde el view");

        setToolbar(mBinding.toolbar, false);

        //listener for the appbar
        mBinding.appBar.addOnOffsetChangedListener(this);

        //set state of appbar (handle rotation)
        mBinding.appBar.setExpanded(ViewModelProviders.of(this).get(ReposViewmodel.class).userInfoExpanded);

        //set the mAdapter for the recycler view
        mRecyclerView = mBinding.contentList.repoList;

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //add a divider decorator
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mScrollListener = new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) mRecyclerView.getLayoutManager(),
                mAdapter) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                log.d(this, "load more data: page-> " +  page + " totalItems-> " + totalItemsCount);
                presenter.getNextBatch();
            }
        };

    }

    @Override
    public void setUserInView(User user) {
        mBinding.setUser(user);
    }

    @Override
    public void setReposInView(List<Repo> repos) {
        mAdapter.addItems(repos);
    }

    @Override
    public void addLifecycleObserver(ReposLifecycleObserver observer) {
        if(observer instanceof LifecycleObserver) {
            getLifecycle().addObserver((LifecycleObserver) observer);
        }
    }

    @Override
    public CustomLivedata<List<Repo>> getLiveRepos() {
        return ViewModelProviders.of(this).get(ReposViewmodel.class).getRepos();
    }

    @Override
    public CustomLivedata<User> getLiveUser() {
        return ViewModelProviders.of(this).get(ReposViewmodel.class).getUser();
    }

    @Override
    public CustomLivedata<PayconiqConstants.STATUS_RESPONSE> getLiveStatus() {
        return ViewModelProviders.of(this).get(ReposViewmodel.class).getStatus();
    }

    @Override
    public int getLastPageRequested() {
        return ViewModelProviders.of(this).get(ReposViewmodel.class).lastPageRequested;
    }

    @Override
    public void showProgressBar() {
        mAdapter.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        mAdapter.hideProgressBar();
    }

    @Override
    public void setLastPageRequested(int page) {
        ViewModelProviders.of(this).get(ReposViewmodel.class).lastPageRequested = page;
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(mBinding.getRoot(), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void listenToScrollEvents(boolean listen) {
        ViewModelProviders.of(this).get(ReposViewmodel.class).canListenScrollEvents = listen;
    }


    //region SCROLL METHODS
    private void setScrollListener(){
        //check if need to add listener (if data came from local db, avoid it)
        if(ViewModelProviders.of(this).get(ReposViewmodel.class).canListenScrollEvents) {
            mRecyclerView.addOnScrollListener(mScrollListener);
        }
    }

    private void removeScrollListener(){
        mRecyclerView.clearOnScrollListeners();
    }
    //enregion

    //region interfaza appbarlayout
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(Math.abs(verticalOffset) > appBarLayout.getTotalScrollRange() - 5){  //5 is a small threshold
            //collapsed -> save state of appbar
            ViewModelProviders.of(this).get(ReposViewmodel.class).userInfoExpanded = false;
            setScrollListener();
        }else {
            ViewModelProviders.of(this).get(ReposViewmodel.class).userInfoExpanded = true;
            removeScrollListener();
        }
    }

    //endregion
}
