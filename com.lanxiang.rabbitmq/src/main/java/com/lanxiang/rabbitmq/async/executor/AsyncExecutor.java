package com.lanxiang.rabbitmq.async.executor;

import com.lanxiang.rabbitmq.async.message.AsyncMessage;

/**
 * Created by lanxiang on 2016/9/29.
 */
public interface AsyncExecutor {

    int executeWorks(byte[] bytes) throws Throwable;

    AsyncMessage receiveMessageBody(byte[] bytes) throws Throwable;
}
