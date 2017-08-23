package com.rukiasoft.payconiqtest.persistence.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;

import java.util.List;

import javax.inject.Singleton;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Roll on 11/8/17.
 */

@Dao
public interface RepoDao {
    @Query("SELECT * FROM repo")
    List<Repo> getAll();

    @Query("SELECT * FROM repo "
            + "WHERE id IN (:userIds) "
            + "ORDER BY repo.id "
            + "LIMIT :nRows OFFSET :startingRow "
    )
    List<Repo> loadAllByIds(int[] userIds, int nRows, int startingRow);

    @Query("SELECT * FROM repo "
            + "INNER JOIN user ON user.id = repo.user_id "
            + "WHERE user.name LIKE :userName "
            + "ORDER BY repo.id "
            + "LIMIT :nRows OFFSET :startingRow "
    )
    List<Repo> loadAllByOwnerName(String userName, int nRows, int startingRow);

    @Query("SELECT * FROM repo WHERE user_id = :userIds")
    List<Repo> loadAllByUserId(int userIds);

    @Insert(onConflict = REPLACE)
    void insertAll(Repo... repos);

    @Delete
    void delete(Repo repo);
}
