package com.lanxiang.rabbitmq.resource;

import com.google.inject.Provider;
import com.rabbitmq.client.Connection;

/**
 * Created by lanxiang on 2016/9/26.
 */
public abstract class AbstractRabbitmqConnectionProvider implements Provider<Connection> {

    protected Connection connection;

    @Override
    public Connection get() {
        return null;
    }
}
