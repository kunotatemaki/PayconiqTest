package com.rukiasoft.payconiqtest.model;

/**
 * Created by Roll on 10/8/17.
 */

public class User {
    private String name;
    private String imageUrl;

    public User(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
