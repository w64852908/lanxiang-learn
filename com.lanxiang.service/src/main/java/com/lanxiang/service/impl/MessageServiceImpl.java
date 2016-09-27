package com.lanxiang.service.impl;

import com.google.inject.Inject;
import com.lanxiang.rabbitmq.async.annotation.AsyncMark;
import com.lanxiang.rabbitmq.async.register.AsyncRegister;
import com.lanxiang.service.MessageService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

/**
 * Created by lanxiang on 2016/9/26.
 */
@Slf4j
@Singleton
public class MessageServiceImpl implements MessageService {

    @Inject
    private AsyncRegister asyncRegister;

    @PostConstruct
    public void initMessageService() {
        asyncRegister.register(this);
    }

    public void testMessageService() {
        log.info("========== Access message service. ==========");
        log.info(asyncRegister.toString());
        asyncRegister.exposeAllRegister();
    }
}
