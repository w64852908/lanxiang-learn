package com.lanxiang.guava.cache.methodinterceptor.interceptor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.lanxiang.guava.cache.methodinterceptor.annotation.Cached;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lanxiang on 2016/10/12.
 */
@Slf4j
public class CacheInterceptor implements MethodInterceptor {

    /**
     * key - 需要缓存的方法
     * value - 对应的缓存
     */
    private final ConcurrentHashMap<String, Cache<Object, Object>> caches;

    public CacheInterceptor(ConcurrentHashMap<String, Cache<Object, Object>> caches) {
        this.caches = caches;
    }

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {

        //作为缓存的key
        String key = getKey(invocation);
        //获取被拦截方法的@Cached注解及其内容
        Cached cached = invocation.getStaticPart().getAnnotation(Cached.class);
        //获取方法的全部信息,作为hashmap的key
        String method = invocation.getMethod().toString();
        //把方法和对应缓存存储到hashmap中
        caches.putIfAbsent(method, CacheBuilder.newBuilder()
                .expireAfterWrite(cached.expireTime(), cached.timeUnit())
                .build());

        Cache<Object, Object> cache = caches.get(method);

        /**
         * get/getIfPresent方法当参数为null时会抛空指针异常
         * 因此invocation.proceed所执行的函数,返回值不能为null
         */
        return cache.get(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    return invocation.proceed();
                } catch (Throwable t) {
                    log.error("message : " + t + ", cause : " + t);
                    throw new RuntimeException(t);
                }
            }
        });
    }

    //生成用于存储在缓存中的唯一id
    private String getKey(MethodInvocation invocation) {
        StringBuilder key = new StringBuilder();

        key.append(invocation.getThis().getClass().getSuperclass())
                .append("#")
                .append(invocation.getMethod().getName())
                .append("[");

        for (Object o : invocation.getArguments()) {
            if (o == null) {
                key.append("null");
            } else {
                key.append(o.hashCode()).append(",");
            }
        }
        key.append("]");
        return key.toString();
    }
}