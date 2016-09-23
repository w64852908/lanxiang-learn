package com.lanxiang.rabbitmq.review.topic.consumer;

import com.lanxiang.rabbitmq.review.topic.consumer.MessageConsumer;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.List;

/**
 * Created by lanxiang on 16/9/19.
 */
public abstract class MessageTopicConsumer extends MessageConsumer {

    protected MessageTopicConsumer() {
        super();
    }

    @Override
    public Channel declareChannel() throws IOException {
        channel.exchangeDeclare(getExchange(), getType(), true, false, null);
        for (String bindingKey : getBindingKeys()) {
            queue = declareQueue();
            channel.queueBind(queue, getExchange(), bindingKey);
        }
        return channel;
    }

    @Override
    public String getType() {
        return "topic";
    }

    @Override
    public String getExchange() {
        return "topic_logs";
    }

    @Override
    public String declareQueue() throws IOException {
        return channel.queueDeclare(getQueueName(), true, false, false, null).getQueue();
    }

    @Override
    public abstract List<String> getBindingKeys();

    public abstract String getQueueName();
}