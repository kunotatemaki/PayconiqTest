package com.rukiasoft.payconiqtest.model;

/**
 * Created by Roll on 10/8/17.
 */

public class Repo {
    private String name;

    private String description;

    public Repo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
