package com.lanxiang.servlet.guice;

import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import com.lanxiang.servlet.CharacterEncodingFilter;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Created by lanxiang on 2016/11/11.
 */
@Slf4j
public class ServletRuntimeModule extends ServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> encodeParam = new HashMap<>();
        encodeParam.put("encoding", "UTF-8");
        encodeParam.put("forceEncoding", "true");
        filter("/*").through(CharacterEncodingFilter.class, encodeParam);

        String apiPath = "/*";
        filter(apiPath).through(ServletContainer.class);
        loadFromClasspath();
    }

    @Provides
    @Singleton
    public ServletContainer getServletContainer(ResourceConfig config) {
        Map<String, Object> appParams = new HashMap<>();
        appParams.put(ServletProperties.FILTER_FORWARD_ON_404, Boolean.TRUE);
        appParams.put(ServletProperties.FILTER_STATIC_CONTENT_REGEX,
                "(/(bower_components|fonts|images|scripts|styles|views|version.*)/.*)|(\\w*\\.\\w+)");
        appParams.put(ServerProperties.WADL_FEATURE_DISABLE, Boolean.FALSE);
        config.addProperties(appParams);
        return new ServletContainer(config);
    }

    private void loadFromClasspath() {
        int count = 0;
        for (ServletModule module : ServiceLoader.load(ServletModule.class)) {
            log.debug("Install " + module.getClass().getName());
            install(module);
            count++;
        }
        log.info("{} modules was installed.", count);
    }
}
