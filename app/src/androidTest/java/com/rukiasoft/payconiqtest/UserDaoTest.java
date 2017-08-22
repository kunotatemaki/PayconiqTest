package com.rukiasoft.payconiqtest;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.rukiasoft.payconiqtest.persistence.daos.UserDao;
import com.rukiasoft.payconiqtest.persistence.databases.PayconiqDatabase;
import com.rukiasoft.payconiqtest.persistence.entities.User;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Roll on 22/8/17.
 */


@RunWith(AndroidJUnit4.class)
public class UserDaoTest {
    private UserDao mUserDao;
    private PayconiqDatabase mDb;
    User user1, user2;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, PayconiqDatabase.class).build();
        mUserDao = mDb.userDao();
        user1 = new User(1, "John", "www.photo1.com");
        user2 = new User(2, "Mike", "www.photo2.com");
        User[] users = {user1, user2};
        mUserDao.insertAll(users);

    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        List<User> lUsers = mUserDao.getAll();
        assertTrue(lUsers.size() == 2);
    }

    @Test
    public void writeUsersAndReadOne() throws Exception {
        User userFromDb = mUserDao.findByName("Mike");
        assertTrue(userFromDb.getName().equals("Mike"));
    }

    @Test
    public void deleteUser() throws Exception {
        mUserDao.delete(user2);
        List<User> lUsers = mUserDao.getAll();
        assertTrue(lUsers.size() == 1);
    }
}