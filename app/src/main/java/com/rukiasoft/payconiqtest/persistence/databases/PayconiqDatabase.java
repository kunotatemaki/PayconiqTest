package com.rukiasoft.payconiqtest.persistence.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.rukiasoft.payconiqtest.persistence.daos.RepoDao;
import com.rukiasoft.payconiqtest.persistence.daos.UserDao;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;

/**
 * Created by Roll on 11/8/17.
 */

@Database(entities = {User.class, Repo.class}, version = 1)
public abstract class PayconiqDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RepoDao repoDao();
}