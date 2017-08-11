package com.rukiasoft.payconiqtest.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Roll on 10/8/17.
 */

@Entity(indices = {@Index(value = {"name"},
        unique = true)})
public class User {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "image_url")
    private String imageUrl;

    public User(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
