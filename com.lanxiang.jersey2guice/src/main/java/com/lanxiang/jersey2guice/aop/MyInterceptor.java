package com.lanxiang.jersey2guice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lanxiang on 2016/11/30.
 */

@Slf4j
public class MyInterceptor implements MethodInterceptor {

    public final AtomicInteger counter = new AtomicInteger();

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("Method invocate {} times. ", counter.incrementAndGet());
        return methodInvocation.proceed();
    }
}
