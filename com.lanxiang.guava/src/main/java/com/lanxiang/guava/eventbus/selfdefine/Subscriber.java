package com.lanxiang.guava.eventbus.selfdefine;

import java.lang.reflect.Method;

/**
 * Created by lanxiang on 2017/4/12.
 */
public class Subscriber {

    private final Object target;

    private final Method method;

    public Subscriber(Object target, Method method) {
        if (target == null || method == null) {
            throw new IllegalArgumentException("Target object and method must not be null");
        }
        this.target = target;
        this.method = method;
        //值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查,可以提高性能
        this.method.setAccessible(true);
    }

    public void invoke(Object parameter) throws Exception {
        method.invoke(target, parameter);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "target=" + target +
                ", method=" + method +
                '}';
    }
}