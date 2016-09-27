package com.lanxiang.rabbitmq.async.guice;

import com.google.inject.PrivateModule;
import com.lanxiang.rabbitmq.async.register.AsyncRegister;
import com.lanxiang.rabbitmq.async.register.AsyncRegisterProvider;

/**
 * Created by lanxiang on 2016/9/26.
 */
public class AsyncModule extends PrivateModule {

    @Override
    protected void configure() {
        bind(AsyncRegister.class).toProvider(AsyncRegisterProvider.class).asEagerSingleton();
    }
}
