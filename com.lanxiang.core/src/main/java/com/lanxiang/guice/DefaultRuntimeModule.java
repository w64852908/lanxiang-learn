package com.lanxiang.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.lanxiang.core.Environment;
import lombok.extern.slf4j.Slf4j;

import java.util.ServiceLoader;

/**
 * Created by lanxiang on 2016/11/10.
 */
@Slf4j
public class DefaultRuntimeModule extends AbstractModule {

    private ClassLoader classLoader;

    public DefaultRuntimeModule() {
        this(Thread.currentThread().getContextClassLoader());
    }

    public DefaultRuntimeModule(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    protected void configure() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });

//        GuiceEnvironment environment = new GuiceEnvironment();
//        bind(Environment.class).toInstance(environment);
//        //静态注入,但是没有带有inject注解的静态属性
//        requestStaticInjection(GuiceEnvironment.class);
        //TODO 加载配置文件Module
        install(new ConfigurationModule());
        installServiceModules();
    }

    //加载所有项目中的module
    private void installServiceModules() {
        for (Module module : ServiceLoader.load(Module.class, classLoader)) {
            log.info("Install {}", module.getClass().getName());
            install(module);
        }
    }
}
