package com.lanxiang.mybatis.run;

import com.google.inject.Guice;
import com.lanxiang.mybatis.dao.UserDAO;
import com.lanxiang.mybatis.guice.RunModule;
import com.lanxiang.mybatis.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 2017/1/23.
 */
public class TestTransaction {

    private UserDAO userDAO;

    @Before
    public void init() {
        userDAO = Guice.createInjector(new RunModule()).getInstance(UserDAO.class);
    }

    @Test
    public void run() {
        User user = new User(null, "lanxiang", 127);
        userDAO.createUser(user);
    }

    @Test
    public void run2() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User((long) i, "lanxiang" + i, 23);
            userList.add(user);
        }
        userDAO.createUsers(userList);
    }
}
