package com.rukiasoft.payconiqtest.persistence.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rukiasoft.payconiqtest.persistence.entities.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Roll on 11/8/17.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE name LIKE :first  LIMIT 1")
    User findByName(String first);

    @Insert(onConflict = REPLACE)
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
