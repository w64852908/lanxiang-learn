package com.lanxiang.test.rabbitmq.service;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.lanxiang.rabbitmq.async.annotation.AsyncMark;
import com.lanxiang.rabbitmq.async.guice.AsyncModule;
import com.lanxiang.rabbitmq.async.register.AsyncRegister;
import com.lanxiang.test.rabbitmq.event.RemindEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lanxiang on 2016/9/29.
 */

@Slf4j
public class AsyncService {

    @Inject
    AsyncRegister asyncRegister;

    @AsyncMark
    public void showRemind(RemindEvent remindEvent) {

    }

    @Before
    public void init() {

        Injector injector = Guice.createInjector(new AsyncModule());
        asyncRegister = injector.getProvider(AsyncRegister.class).get();
        asyncRegister.register(this);
    }

    @Test
    public void test() {
        AsyncService service = new AsyncService();
        asyncRegister.exposeAllRegister();
    }
}
