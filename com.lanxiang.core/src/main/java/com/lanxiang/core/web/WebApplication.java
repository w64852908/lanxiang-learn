package com.lanxiang.core.web;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Feature;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by lanxiang on 2016/11/21.
 */
public class WebApplication extends Application {

    private Set<Class<?>> classes;

    private Set<Object> singletons;

    private Map<String, Object> properties;

    public WebApplication() {
        classes = new LinkedHashSet<>();
        singletons = new LinkedHashSet<>();
        properties = new HashMap<>();
    }

    //判断是否为web资源
    public static final boolean isWebResource(Class clazz) {
        return clazz.getAnnotation(Path.class) != null
                || clazz.getAnnotation(Provider.class) != null
                || Feature.class.isAssignableFrom(clazz);
    }

    public WebApplication register(Class<?> clazz) {
        classes.add(clazz);
        return this;
    }

    public WebApplication registerClasses(Class<?>... classes) {
        for (Class clazz : classes) {
            register(clazz);
        }
        return this;
    }

    public WebApplication registerInstance(Object instance) {
        singletons.add(instance);
        return this;
    }

    public WebApplication property(String name, Object value) {
        properties.put(name, value);
        return this;
    }

    @Override
    public Set<Class<?>> getClasses(){
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }
}
