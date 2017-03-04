package com.lanxiang.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.lanxiang.jackson.BaseObjectMapperProvider;
import com.lanxiang.jersey.JerseyEventListener;

/**
 * Created by lanxiang on 2016/11/18.
 */
public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ObjectMapper.class).toProvider(BaseObjectMapperProvider.class).asEagerSingleton();
    }
}
