package com.lanxiang.apidoc.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.lanxiang.apidoc.ApiDocResource;
import io.swagger.config.ScannerFactory;
import io.swagger.jaxrs.config.DefaultJaxrsScanner;
import io.swagger.models.Info;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 2016/11/22.
 */
@Slf4j
public class ApiDocModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ApiDocResource.class);

        DefaultJaxrsScanner scanner = new DefaultJaxrsScanner();
        ScannerFactory.setScanner(scanner);
    }

    @Singleton
    @Provides
    public Swagger getSwagger() {
        log.info("get swagger singleton");
        Swagger swagger = new Swagger();
        swagger.setInfo(new Info().version("1.0.0"));
        List<Scheme> schemes = new ArrayList<>();
        schemes.add(Scheme.HTTP);
        swagger.setSchemes(schemes);
        return swagger;
    }
}
