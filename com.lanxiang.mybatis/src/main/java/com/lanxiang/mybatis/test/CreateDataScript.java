package com.lanxiang.mybatis.test;

import com.google.inject.Guice;
import com.lanxiang.mybatis.guice.RunModule;
import com.lanxiang.mybatis.mapper.UserMapper;
import com.lanxiang.mybatis.model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lanxiang on 2017/4/15.
 */
public class CreateDataScript {

    private UserMapper mapper;

    @Before
    public void init() {
        mapper = Guice.createInjector(new RunModule()).getInstance(UserMapper.class);
    }

    @Test
    public void run() {
        for (int i = 0; i < 2000000; i++) {
            mapper.insert(new User((long) i, "lanxiang" + i, (i + 10) % 100));
        }
    }
}
