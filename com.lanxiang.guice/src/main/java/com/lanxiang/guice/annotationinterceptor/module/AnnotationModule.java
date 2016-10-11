package com.lanxiang.guice.annotationinterceptor.module;

import com.lanxiang.guice.annotationinterceptor.annotation.SystemOut;
import com.lanxiang.guice.annotationinterceptor.interceptor.SystemOutInterceptor;
import com.lanxiang.guice.annotationinterceptor.service.RunService;
import com.lanxiang.guice.annotationinterceptor.service.RunServiceImpl;
import com.google.inject.PrivateModule;
import com.google.inject.matcher.Matchers;

/**
 * Created by lanxiang on 16/9/18.
 */
public class AnnotationModule extends PrivateModule {

    protected void configure() {

        bind(RunService.class).to(RunServiceImpl.class).asEagerSingleton();

        expose(RunService.class);

        bind(RunServiceImpl.class);

        expose(RunServiceImpl.class);

        SystemOutInterceptor systemOutInterceptor = new SystemOutInterceptor();

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(SystemOut.class), systemOutInterceptor);
    }
}
