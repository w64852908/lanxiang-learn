package com.lanxiang.guice;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.lanxiang.core.Environment;

import javax.inject.Inject;
import java.lang.annotation.Annotation;

/**
 * Created by lanxiang on 2016/11/10.
 */
public class GuiceEnvironment extends Environment {

    @Inject
    private Injector injector;

    @Override
    public <T> T getInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    @Override
    public <T> T getInstance(Class<T> objectType, String named) {
        return injector.getInstance(Key.get(objectType, Names.named(named)));
    }

    @Override
    public <T> T getInstance(Class<T> objectType, Annotation annotation) {
        return injector.getInstance(Key.get(objectType, annotation));
    }
}
