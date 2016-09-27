package com.lanxiang.rabbitmq.async.register;


import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;

/**
 * Created by lanxiang on 2016/9/26.
 */
@Slf4j
@Singleton
public class AsyncRegisterProvider implements Provider<AsyncRegister> {

    private static AsyncRegister register;

    @Override
    public AsyncRegister get() {
        if (register == null) {
            register = new AsyncRegister();
        }
        return register;
    }

    @PreDestroy
    public void close() {
        register.clear();
    }
}
