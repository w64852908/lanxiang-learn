package com.lanxiang.core.web.guice;

import com.google.inject.*;
import com.lanxiang.core.web.ObjectMapperResolver;
import com.lanxiang.core.web.WebApplication;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by lanxiang on 2016/11/21.
 */

@Slf4j
public class CoreWebModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ObjectMapperResolver.class).asEagerSingleton();
    }

    @Singleton
    @Provides
    ResourceConfig getResourceConfig(Injector injector) {
        WebApplication webApp = new WebApplication();
        for (Map.Entry<Key<?>, Binding<?>> entry : injector.getAllBindings().entrySet()) {
            Class rawType = entry.getKey().getTypeLiteral().getRawType();
            if (WebApplication.isWebResource(rawType)) {
                webApp.register(rawType);
            }
        }
        //
        if (log.isDebugEnabled()) {
            webApp.registerInstance(new LoggingFilter(Logger.getLogger(LoggingFilter.class.getName()), true));
        }
        ResourceConfig config = ResourceConfig.forApplication(webApp);
        config.setApplicationName("wbg_start_process");
        return config;
    }
}
