package com.lanxiang.rabbitmq.async.executor;


/**
 * Created by lanxiang on 2016/9/29.
 */
public interface AsyncExecutor {

    int executeWorks(byte[] bytes) throws Throwable;
}
