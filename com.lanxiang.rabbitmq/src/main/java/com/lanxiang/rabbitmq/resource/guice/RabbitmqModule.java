package com.lanxiang.rabbitmq.resource.guice;

import com.google.inject.PrivateModule;
import com.lanxiang.rabbitmq.resource.DefaultRabbitmqConnectionProvider;
import com.lanxiang.rabbitmq.resource.RabbitmqChannelProvider;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Created by lanxiang on 2016/9/28.
 */
public class RabbitmqModule extends PrivateModule {

    @Override
    protected void configure() {
        bind(Connection.class).toProvider(DefaultRabbitmqConnectionProvider.class).asEagerSingleton();
        expose(Connection.class);

        bind(Channel.class).toProvider(RabbitmqChannelProvider.class).asEagerSingleton();
        expose(Channel.class);
    }
}
