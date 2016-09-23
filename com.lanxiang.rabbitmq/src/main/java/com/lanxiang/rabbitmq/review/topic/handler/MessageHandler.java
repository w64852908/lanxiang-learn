package com.lanxiang.rabbitmq.review.topic.handler;

import java.lang.reflect.Method;

/**
 * Created by lanxiang on 16/9/20.
 */
public class MessageHandler {

    private final Object target;

    private final Method method;

    public MessageHandler(Object target, Method method) {
        this.target = target;
        this.method = method;
        method.setAccessible(true);
    }

    public void invokeMethod(Object parameter) throws Throwable {
        method.invoke(target, parameter);
    }
}