package com.lanxiang.mybatis.dao.impl;

import com.lanxiang.mybatis.dao.UserDAO;
import com.lanxiang.mybatis.mapper.UserMapper;
import com.lanxiang.mybatis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionManager;
import org.mybatis.guice.transactional.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lanxiang on 2017/1/23.
 */

@Slf4j
public class UserDAOImpl implements UserDAO {

    @Inject
    private UserMapper userMapper;

    @Inject
    private SqlSessionManager sessionManager;

    @Override
    public Integer createUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    @Transactional
    public void createUsers(List<User> userList) throws RuntimeException {
        batchInsert(userList);
    }

    private void batchInsert(List<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            if (i == userList.size() / 2) {
//                throw new IllegalArgumentException("roll back");
            }
            userMapper.insert(userList.get(i));
        }
    }

//    @Transactional
//    private void batchInsert(List<User> userList) {
//        this.sessionManager.startManagedSession(ExecutorType.BATCH, TransactionIsolationLevel.READ_UNCOMMITTED);
//        try {
//            for (int i = 0; i < userList.size(); i++) {
//                if (i == userList.size() / 2) {
//                    throw new RuntimeException("roll back");
//                }
//                userMapper.insert(userList.get(i));
//            }
//            this.sessionManager.commit();
//        } catch (Exception e) {
//            this.sessionManager.rollback();
//            log.info("Batch insert user method roll back.");
//            throw new IllegalArgumentException("Something is wrong.");
//        } finally {
//            this.sessionManager.close();
//        }
//    }
}
