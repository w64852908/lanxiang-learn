package com.lanxiang.guava.cache.methodinterceptor.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lanxiang on 2016/10/11.
 */

@Retention(RUNTIME)
@Target(METHOD)
public @interface Cached {

    long expireTime();

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
