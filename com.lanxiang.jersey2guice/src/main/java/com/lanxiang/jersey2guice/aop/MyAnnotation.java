package com.lanxiang.jersey2guice.aop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * Created by lanxiang on 2016/11/30.
 */

@Target(METHOD)
@Retention(RUNTIME)
public @interface MyAnnotation {
}
