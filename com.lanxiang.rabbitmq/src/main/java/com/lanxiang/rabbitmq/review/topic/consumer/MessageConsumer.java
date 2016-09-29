package com.lanxiang.rabbitmq.review.topic.consumer;

import com.lanxiang.rabbitmq.review.topic.channel.ChannelGenerator;
import com.lanxiang.rabbitmq.review.topic.executor.TopicExecutor;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;

/**
 * Created by lanxiang on 16/9/19.
 */
@Singleton
public abstract class MessageConsumer {

    protected final static Logger log = LoggerFactory.getLogger(Consumer.class);

    private Consumer consumer;

    Channel channel;

    String queue;

    private TopicExecutor topicExecutor;

    protected MessageConsumer() {
        log.info("Initialize rabbitmq consumer channel.");
        ChannelGenerator channelGenerator = new ChannelGenerator();
        topicExecutor = new TopicExecutor();
        try {
            channel = channelGenerator.getChannel();
            declareChannel();
        } catch (Exception e) {
            log.error("Failed to declare channel, " + e.getMessage());
            throw new IllegalStateException("Can't connect to rabbitmq.");
        }

        consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                log.info("[x] received routingKey = " + envelope.getRoutingKey() + ", msg = " + message);
                topicExecutor.executeWork(body);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        try {
            channel.basicConsume(queue, false, consumer);
        } catch (IOException e) {
            log.error("Consume message error, " + e.getMessage());
        }
    }


    public abstract Channel declareChannel() throws IOException;

    public abstract String getType();

    public abstract String getExchange();

    public abstract String declareQueue() throws IOException;

    public abstract List<String> getBindingKeys();
}
