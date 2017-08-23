package com.rukiasoft.payconiqtest.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rukiasoft.payconiqtest.model.CustomLivedata;
import com.rukiasoft.payconiqtest.network.endpoints.RetrofitEndpoints;
import com.rukiasoft.payconiqtest.network.responsemodel.GithubRepos;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;
import com.rukiasoft.payconiqtest.utils.logger.LoggerHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Roll on 10/8/17.
 */

public class NetworkManagerImpl implements NetworkManager {

    @Inject
    LoggerHelper log;

    @Inject
    Context context;

    private final Retrofit retrofit;

    @Inject
    public NetworkManagerImpl() {
        retrofit = new Retrofit.Builder()
                .baseUrl(PayconiqConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public boolean isNetworkAvailable() {
        // TODO: 11/8/17 hacer esta función
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    @Override
    public void getDataFromGithub(int page,
                                  final CustomLivedata<PayconiqConstants.STATUS_RESPONSE> status,
                                  final CustomLivedata<User> user,
                                  final CustomLivedata<List<Repo>> repos,
                                  final String ownerName
    ) {
        RetrofitEndpoints endpoints = retrofit.create(RetrofitEndpoints.class);

        final Call<List<GithubRepos>> call = endpoints.getRepos(ownerName,
                page, PayconiqConstants.PER_PAGE_VALUE);
        call.enqueue(new Callback<List<GithubRepos>>() {
            @Override
            public void onResponse(Call<List<GithubRepos>> call, Response<List<GithubRepos>> response) {
                log.d(this, "respuesta ok");
                if(response.body() != null) {
                    List<GithubRepos> listResponse = response.body();
                    if(listResponse == null ){
                        status.setLivedataValue(PayconiqConstants.STATUS_RESPONSE.LOAD_FAILED);
                        return;
                    }
                    User lUser = null;

                    //get stored repos to add new ones
                    List<Repo> repoList = repos.getLivedataValue();
                    if(repoList == null){
                        repoList = new ArrayList<>();
                    }
                    for(GithubRepos githubResponse : listResponse){
                        if(lUser == null){
                            lUser = new User(githubResponse.getUser().getId(),
                                    githubResponse.getUser().getLogin(),
                                    githubResponse.getUser().getAvatarUrl());
                        }
                        Repo repo = new Repo(githubResponse.getId(),
                                githubResponse.getUser().getId(),
                                githubResponse.getName(),
                                githubResponse.getDescription());
                        repoList.add(repo);
                    }

                    //update livedata values and force to store in local database
                    if(lUser != null) {
                        user.forceStorageInLocalDatabaseOnNewData(true);
                        user.setLivedataValue(lUser);
                    }
                    repos.forceStorageInLocalDatabaseOnNewData(true);
                    repos.setLivedataValue(repoList);
                    if(listResponse.isEmpty()){
                        status.setLivedataValue(PayconiqConstants.STATUS_RESPONSE.NO_MORE_REPOS);
                    }else {
                        status.setLivedataValue(PayconiqConstants.STATUS_RESPONSE.LOAD_OK);
                    }
                }else{
                    log.d(this, "respuesta vacía");
                    status.setLivedataValue(PayconiqConstants.STATUS_RESPONSE.LOAD_FAILED);
                }

            }

            @Override
            public void onFailure(Call<List<GithubRepos>> call, Throwable t) {
                log.d(this, "respuesta mal: " + t.getMessage());
                status.setLivedataValue(PayconiqConstants.STATUS_RESPONSE.LOAD_FAILED);
            }
        });
    }
}
