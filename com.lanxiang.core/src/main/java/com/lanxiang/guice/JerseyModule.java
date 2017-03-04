package com.lanxiang.guice;

import com.google.inject.AbstractModule;
import com.lanxiang.jersey.JerseyEventListener;

/**
 * Created by lanxiang on 2016/11/21.
 */
public class JerseyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(JerseyEventListener.class);
    }
}
