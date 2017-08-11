package com.rukiasoft.payconiqtest.network.implementations;

import com.rukiasoft.payconiqtest.model.Repo;
import com.rukiasoft.payconiqtest.model.User;
import com.rukiasoft.payconiqtest.model.livedata.CustomLivedata;
import com.rukiasoft.payconiqtest.network.NetworkManager;
import com.rukiasoft.payconiqtest.network.endpoints.RetrofitEndpoints;
import com.rukiasoft.payconiqtest.network.responsemodel.GithubRepos;
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
        return true;
    }

    @Override
    public void getDataFromGithub(int page,
                                  final CustomLivedata<PayconiqConstants.StatusResponse> status,
                                  final CustomLivedata<User> user,
                                  final CustomLivedata<List<Repo>> repos
    ) {
        RetrofitEndpoints endpoints = retrofit.create(RetrofitEndpoints.class);

        final Call<List<GithubRepos>> call = endpoints.getRepos(PayconiqConstants.NICKNAME,
                page, PayconiqConstants.PER_PAGE_VALUE);
        call.enqueue(new Callback<List<GithubRepos>>() {
            @Override
            public void onResponse(Call<List<GithubRepos>> call, Response<List<GithubRepos>> response) {
                log.d(this, "respuesta ok");
                if(response.body() != null) {
                    List<GithubRepos> listResponse = response.body();
                    if(listResponse == null && listResponse.isEmpty()){
                        status.setLivedataValue(PayconiqConstants.StatusResponse.NO_MORE_REPOS);
                        return ;
                    }
                    User lUser = null;
                    List<Repo> repoList = new ArrayList<>();
                    for(GithubRepos githubResponse : listResponse){
                        if(lUser == null){
                            lUser = new User(githubResponse.getUser().getLogin(),
                                    githubResponse.getUser().getAvatarUrl());
                        }
                        Repo repo = new Repo(githubResponse.getName(),
                                githubResponse.getDescription());
                        repoList.add(repo);
                    }
                    //update livedata values
                    user.setLivedataValue(lUser);
                    repos.setLivedataValue(repoList);
                    status.setLivedataValue(PayconiqConstants.StatusResponse.DOWNLOAD_OK);
                }

            }

            @Override
            public void onFailure(Call<List<GithubRepos>> call, Throwable t) {
                log.d(this, "respuesta mal: " + t.getMessage());
                status.setLivedataValue(PayconiqConstants.StatusResponse.DOWNLOAD_FAILED);
                // TODO: 11/8/17 hacer algo para que el usuario se entere de que ha ido algo mal
            }
        });
    }
}
