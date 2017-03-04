package com.lanxiang.core;

import java.lang.annotation.Annotation;

/**
 * Created by lanxiang on 2016/11/10.
 */
public abstract class Environment {

    public abstract <T> T getInstance(Class<T> clazz);

    public abstract <T> T getInstance(Class<T> objectType, String named);

    public abstract <T> T getInstance(Class<T> objectType, Annotation annotation);

    private static Environment instance;

    public static Environment current() {
        return instance;
    }

    protected Environment(){
        instance = this;
    }
}
