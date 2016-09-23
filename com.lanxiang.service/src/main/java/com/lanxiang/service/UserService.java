package com.lanxiang.service;

import com.lanxiang.model.User;
import com.lanxiang.model.dto.UserDTO;

/**
 * Created by lanxiang on 16/9/1.
 */
public interface UserService {

    void createUser(UserDTO userDTO);

    void deleteUser(String name);

    void updateUser(UserDTO userDTO);

    User findUserByName(String name);
}
