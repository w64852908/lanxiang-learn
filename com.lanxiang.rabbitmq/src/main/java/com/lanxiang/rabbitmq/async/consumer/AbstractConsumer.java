package com.lanxiang.rabbitmq.async.consumer;

import com.google.inject.Inject;
import com.lanxiang.rabbitmq.async.executor.AsyncExecutor;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;

/**
 * Created by lanxiang on 2016/9/29.
 */
@Slf4j
@Singleton
public abstract class AbstractConsumer {

    @Inject
    private Connection connection;

    private Channel channel;

    private AsyncExecutor asyncExecutor;

    protected abstract String getExchangeName();

    protected abstract String getDefaultRoutingKey();

    protected abstract String getType();

    protected abstract String getQueueName();

    protected abstract List<String> getBindingKeys();

    public void initConsumer() {
        log.info("Initialize rabbitmq consumer.");
        try {
            channel = connection.createChannel();
            declareExchange();
            declareQueue();
            bindQueue();
        } catch (IOException e) {
            log.error("Create channel failed, " + e.getMessage());
        }
    }

    private void declareExchange() throws IOException {
        channel.exchangeDeclare(getExchangeName(), getType(), true, false, null);
    }

    private void declareQueue() throws IOException {
        channel.queueDeclare(getQueueName(), true, false, false, null).getQueue();
    }

    private void bindQueue() throws IOException {
        for (String bindingKey : getBindingKeys()) {
            channel.queueBind(getQueueName(), getExchangeName(), bindingKey);
        }
    }
}
