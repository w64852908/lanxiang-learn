package com.lanxiang.rabbitmq.async.subscriber;

import java.lang.reflect.Method;

/**
 * Created by lanxiang on 2016/9/23.
 */
public class AsyncSubscriber {

    private final Object targetObject;

    private final Method targetMethod;

    public AsyncSubscriber(Object targetObject, Method targetMethod) {
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.targetMethod.setAccessible(true);
    }

    public void invokeMethod(Object parameter) throws Throwable {
        targetMethod.invoke(targetObject, parameter);
    }

    @Override
    public String toString() {
        return "AsyncSubscriber{" +
                "targetObject=" + targetObject +
                ", targetMethod=" + targetMethod +
                '}';
    }
}