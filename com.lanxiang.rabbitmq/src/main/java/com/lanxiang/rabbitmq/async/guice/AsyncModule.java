package com.lanxiang.rabbitmq.async.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.lanxiang.rabbitmq.async.executor.AsyncExecutor;
import com.lanxiang.rabbitmq.async.executor.AsyncExecutorImpl;
import com.lanxiang.rabbitmq.async.producer.AbstractAsyncProducer;
import com.lanxiang.rabbitmq.async.register.AsyncRegister;
import com.lanxiang.rabbitmq.async.register.AsyncRegisterProvider;

/**
 * Created by lanxiang on 2016/9/26.
 */
public class AsyncModule extends PrivateModule {

    @Override
    protected void configure() {
        bind(AsyncRegister.class).toProvider(AsyncRegisterProvider.class).asEagerSingleton();
        expose(AsyncRegister.class);
        expose(ObjectMapper.class);
        bind(AsyncExecutor.class).to(AsyncExecutorImpl.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}
