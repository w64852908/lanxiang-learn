package com.lanxiang.guava.cache.methodinterceptor.guice;

import com.google.common.cache.Cache;
import com.google.inject.PrivateModule;
import com.google.inject.matcher.Matchers;
import com.lanxiang.guava.cache.methodinterceptor.annotation.Cached;
import com.lanxiang.guava.cache.methodinterceptor.interceptor.CacheInterceptor;
import com.lanxiang.guava.cache.methodinterceptor.service.CacheService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lanxiang on 2016/10/12.
 */
public class CacheModule extends PrivateModule{

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cached.class),
                new CacheInterceptor(new ConcurrentHashMap<String, Cache<Object, Object>>()));

        bind(CacheService.class);
        expose(CacheService.class);
    }
}
