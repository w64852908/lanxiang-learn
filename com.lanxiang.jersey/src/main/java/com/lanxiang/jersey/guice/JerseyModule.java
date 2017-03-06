package com.lanxiang.jersey.guice;

import com.google.inject.PrivateModule;
import com.lanxiang.jersey.client.rest.AccountService;
import com.lanxiang.jersey.client.rest.impl.AccountServiceImpl;
import com.lanxiang.jersey.client.rest.impl.RestServiceImpl;

/**
 * Created by lanxiang on 2017/3/3.
 */
public class JerseyModule extends PrivateModule {

    @Override
    protected void configure() {
        bindRestService();
    }

    private void bindRestService() {
        bind(RestServiceImpl.class).asEagerSingleton();
        expose(RestServiceImpl.class);
        bind(AccountService.class).to(AccountServiceImpl.class).asEagerSingleton();
        expose(AccountService.class);
    }
}
