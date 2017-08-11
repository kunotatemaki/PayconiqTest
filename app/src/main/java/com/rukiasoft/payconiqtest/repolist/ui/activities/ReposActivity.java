package com.rukiasoft.payconiqtest.repolist.ui.activities;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.rukiasoft.payconiqtest.PayconiqApplication;
import com.rukiasoft.payconiqtest.R;
import com.rukiasoft.payconiqtest.databinding.ActivityReposBinding;
import com.rukiasoft.payconiqtest.dependencyinjection.modules.ReposModule;
import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.User;
import com.rukiasoft.payconiqtest.model.livedata.CustomLivedata;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenter;
import com.rukiasoft.payconiqtest.repolist.ui.activities.interfaces.ReposView;
import com.rukiasoft.payconiqtest.repolist.ui.adapters.ReposAdapter;
import com.rukiasoft.payconiqtest.repolist.ui.lifecycleobservers.ReposLifecycleObserver;
import com.rukiasoft.payconiqtest.repolist.ui.viewmodels.ReposViewmodel;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;
import com.rukiasoft.payconiqtest.utils.ui.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class ReposActivity extends BaseActivity implements ReposView {

    @Inject
    ReposPresenter presenter;

    @Inject
    ReposLifecycleObserver observer;

    @Inject
    LoggerHelper log;

    @Inject
    ReposAdapter adapter;

    private ActivityReposBinding mBinding;

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_repos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setUserInView(User user) {
        mBinding.setUser(user);
    }

    @Override
    public void setReposInView(List<Repo> repos) {
        // TODO: 11/8/17 meter los repositorios descargados en la vista
        log.d(this, "LO CONSEGUIIIIIIIIII!!!!!!");
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
    public CustomLivedata<PayconiqConstants.StatusResponse> getLiveStatus() {
        return ViewModelProviders.of(this).get(ReposViewmodel.class).getStatus();
    }

    @Override
    public int getLastPageRequested() {
        return ViewModelProviders.of(this).get(ReposViewmodel.class).lastPageRequested;
    }
}
