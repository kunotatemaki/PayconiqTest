package com.rukiasoft.payconiqtest.network.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Roll on 10/8/17.
 */

public class GithubRepos {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("owner")
    @Expose
    private GithubUser user;

    @SerializedName("description")
    @Expose
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GithubUser getUser() {
        return user;
    }

    public void setUser(GithubUser user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
