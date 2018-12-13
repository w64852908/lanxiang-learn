package com.lanxiang.designpattern.singleton;

/**
 * Created by lanxiang on 2018/12/6.
 */
public enum UserDAOSingleton {

    USER_DAO_SINGLETON();

    private UserDAO userDAO;

    private UserDAOSingleton() {
        userDAO = new UserDAO();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }


    private class UserDAO {

    }
}
