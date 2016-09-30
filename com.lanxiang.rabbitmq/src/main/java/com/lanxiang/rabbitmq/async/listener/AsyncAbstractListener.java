package com.lanxiang.rabbitmq.async.listener;

import com.google.inject.Inject;
import com.lanxiang.rabbitmq.async.register.AsyncRegister;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * Created by lanxiang on 2016/9/26.
 */
@Slf4j
public abstract class AsyncAbstractListener {

    @Inject
    protected AsyncRegister register;

    @PostConstruct
    public final void init() {
        register.register(this);
    }
}
