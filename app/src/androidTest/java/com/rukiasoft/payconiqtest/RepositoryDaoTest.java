package com.rukiasoft.payconiqtest;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.rukiasoft.payconiqtest.persistence.daos.RepoDao;
import com.rukiasoft.payconiqtest.persistence.daos.UserDao;
import com.rukiasoft.payconiqtest.persistence.databases.PayconiqDatabase;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
import static org.junit.Assert.assertTrue;

/**
 * Created by Roll on 22/8/17.
 */


@RunWith(AndroidJUnit4.class)
public class RepositoryDaoTest {
    private RepoDao mRepoDao;
    private UserDao mUserDao;
    private PayconiqDatabase mDb;
    private Repo repo1, repo2, repo3;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, PayconiqDatabase.class).build();
        mRepoDao = mDb.repoDao();

        mUserDao = mDb.userDao();

    }

    private void writeData(){
        repo1 = new Repo(1, 1, "Repo1", "description 1");
        repo2 = new Repo(2, 1, "Repo2", "description 2");
        repo3 = new Repo(3, 1, "Repo3", "description 3");
        Repo[] repos = {repo1, repo2, repo3};
        mRepoDao.insertAll(repos);

        User user1 = new User(1, "John", "www.photo1.com");
        User[] users = {user1};
        mUserDao.insertAll(users);
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeReposAndReadInList() throws Exception {
        writeData();
        List<Repo> lRepos = mRepoDao.getAll();
        assertTrue(lRepos.size() == 3);
    }

    @Test
    public void writeReposAndReadByIds() throws Exception {
        writeData();
        int[] ids = {1,2};
        List<Repo> lRepos = mRepoDao.loadAllByIds(ids);
        assertTrue(lRepos.size() == 2);
    }

    @Test
    public void readReposByUserId() throws Exception {
        writeData();
        List<Repo> lRepos = mRepoDao.loadAllByUserId(1);
        assertTrue(lRepos.size() == 3);
    }

    @Test
    public void readReposByUserName() throws Exception {
        writeData();
        List<Repo> lRepos = mRepoDao.loadAllByOwnerName("John");
        assertTrue(lRepos.size() == 3);
    }

    @Test
    public void deleteUser() throws Exception {
        writeData();
        mRepoDao.delete(repo2);
        List<Repo> lRepos = mRepoDao.getAll();
        assertTrue(lRepos.size() == 2);
    }
}
