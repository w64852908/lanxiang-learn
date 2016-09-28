package com.lanxiang.rabbitmq.resource;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by lanxiang on 2016/9/28.
 */
@Slf4j
public class RabbitmqChannelProvider implements Provider<Channel> {

    @Inject
    private Connection connection;

    private Channel channel;

    private Channel getChannel() {
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            log.error("Can not create channel, " + e.getMessage());
        }
        return channel;
    }

    @Override
    public Channel get() {
        return getChannel();
    }
}
