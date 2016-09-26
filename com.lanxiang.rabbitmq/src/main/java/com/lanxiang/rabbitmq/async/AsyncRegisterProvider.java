package com.lanxiang.rabbitmq.async;


import com.google.inject.Provider;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by lanxiang on 2016/9/26.
 */
public class AsyncRegisterProvider implements Provider<AsyncRegister> {

    private AsyncRegister register;

    @PostConstruct
    public void init() {
        register = new AsyncRegister();
    }

    @Override
    public AsyncRegister get() {
        return register;
    }

    @PreDestroy
    public void close() {
        register.clear();
    }
}
