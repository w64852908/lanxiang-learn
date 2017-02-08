package com.lanxiang.mybatis.dao;

import com.lanxiang.mybatis.model.User;

import java.util.List;

/**
 * Created by lanxiang on 2017/1/23.
 */
public interface UserDAO {

    Integer createUser(User user);

    void createUsers(List<User> userList);
}
