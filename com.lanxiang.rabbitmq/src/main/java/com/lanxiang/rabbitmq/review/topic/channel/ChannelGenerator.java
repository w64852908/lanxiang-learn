package com.lanxiang.rabbitmq.review.topic.channel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by lanxiang on 16/9/19.
 */
public class ChannelGenerator {

    private final static Logger log = LoggerFactory.getLogger(ChannelGenerator.class);

    private ConnectionFactory factory;

    private Connection connection;

    private Channel channel;

    private void initConnection() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            log.error("Failed to open rabbitmq connection, " + e.getMessage());
        }
    }

    public Channel getChannel() {
        initConnection();
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            log.error("Failed to create rabbitmq channel, " + e.getMessage());
        }
        return channel;
    }
}
