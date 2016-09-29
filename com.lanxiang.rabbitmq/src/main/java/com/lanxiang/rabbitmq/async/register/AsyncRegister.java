package com.lanxiang.rabbitmq.async.register;

import com.google.common.reflect.TypeToken;
import com.lanxiang.rabbitmq.async.subscriber.AsyncSubscriber;
import com.lanxiang.rabbitmq.async.annotation.AsyncMark;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by lanxiang on 2016/9/23.
 */
@Singleton
@Slf4j
public class AsyncRegister {

    private Map<Class<?>, List<AsyncSubscriber>> register;

    public AsyncRegister() {
        register = new HashMap<Class<?>, List<AsyncSubscriber>>();
    }

    public void register(Object listener) {
        if (listener == null) {
            return;
        }
        Class<?> clazz = listener.getClass();
        //获取所有的消费者方法的参数
        for (Method method : getAnnotatedMethods(clazz)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            AsyncSubscriber asyncSubscriber = new AsyncSubscriber(listener, method);
            put(eventType, asyncSubscriber);
        }
    }

    //根据加了特定注解的方法,来注册消费者
    private Collection<Method> getAnnotatedMethods(Class<?> clazz) {
        //获取类的信息
        Set<? extends Class<?>> classInfos = TypeToken.of(clazz).getTypes().rawTypes();
        //获取所有带有特定注解的方法
        Set<Method> annotationMethods = new HashSet<Method>();
        for (Class<?> classInfo : classInfos) {
            Method[] methods = classInfo.getDeclaredMethods();
            for (Method method : methods) {
                AsyncMark asyncMark = method.getAnnotation(AsyncMark.class);
                //过滤掉桥接方法
                if (asyncMark != null && !method.isBridge()) {
                    annotationMethods.add(method);
                }
            }
        }
        return annotationMethods;
    }

    public List<AsyncSubscriber> getSubscriber(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return register.get(clazz);
    }

    private void put(Class<?> clazz, AsyncSubscriber subscriber) {
        if (clazz == null || subscriber == null) {
            return;
        }
        if (register.containsKey(clazz)) {
            List<AsyncSubscriber> subscribers = register.get(clazz);
            subscribers.add(subscriber);
        } else {
            List<AsyncSubscriber> subscribers = new LinkedList<AsyncSubscriber>();
            subscribers.add(subscriber);
            register.put(clazz, subscribers);
        }
    }

    //测试用
    public void exposeAllRegister() {
        log.info("Total register : " + register.size());
        for (Class<?> clazz : register.keySet()) {
            List<AsyncSubscriber> list = register.get(clazz);
            for (AsyncSubscriber subscriber : list) {
                log.info("class " + clazz + " registered (" + subscriber.toString() + ") methods.");
            }
        }
    }

    public void clear() {
        register = null;
    }
}