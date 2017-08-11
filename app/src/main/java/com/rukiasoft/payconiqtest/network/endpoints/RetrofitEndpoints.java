package com.rukiasoft.payconiqtest.network.endpoints;

import com.rukiasoft.payconiqtest.network.responsemodel.GithubRepos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Roll on 10/8/17.
 */

public interface RetrofitEndpoints {

    @GET("users/{nickname}/repos")
    Call<List<GithubRepos>> getRepos(@Path("nickname") String nickname,
                                     @Query("page") int page,
                                     @Query("per_page") int per_page);

}

