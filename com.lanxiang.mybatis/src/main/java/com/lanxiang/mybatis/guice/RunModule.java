package com.lanxiang.mybatis.guice;

import com.lanxiang.mybatis.dao.UserDAO;
import com.lanxiang.mybatis.dao.impl.UserDAOImpl;
import com.lanxiang.mybatis.mapper.MessageMapper;
import com.lanxiang.mybatis.mapper.StudentMapper;
import com.lanxiang.mybatis.mapper.UserMapper;

/**
 * Created by lanxiang on 2017/1/23.
 */
public class RunModule extends MySqlModule {

    @Override
    protected void configure() {
        super.configure();
        bind(UserDAO.class).to(UserDAOImpl.class).asEagerSingleton();
        expose(UserDAO.class);
        bind(UserDAOImpl.class);
        expose(UserDAOImpl.class);
    }

    @Override
    protected Class[] mapperClasses() {
        return new Class[]{
                MessageMapper.class,
                StudentMapper.class,
                UserMapper.class
        };
    }
}
