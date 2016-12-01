package com.lanxiang.guice.provides;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

/**
 * Created by lanxiang on 2016/11/21.
 */
@Slf4j
public class AModule extends AbstractModule {

    @Override
    protected void configure() {
        log.info("A module configure method.");
    }

    @Provides
    @Singleton
    WebApplication getWebApplication(Injector injector) {
        log.info("Injector : {}", injector);
        WebApplication webApplication = new WebApplication();
        webApplication.setName("wbg");
        webApplication.setVersion("2.3");
        return webApplication;
    }
}
