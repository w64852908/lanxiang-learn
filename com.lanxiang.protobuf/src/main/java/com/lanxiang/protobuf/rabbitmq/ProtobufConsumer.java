package com.lanxiang.protobuf.rabbitmq;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.lanxiang.protobuf.Greeting;
import com.lanxiang.rabbitmq.resource.guice.RabbitmqModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by lanxiang on 2016/10/9.
 */

@Slf4j
public class ProtobufConsumer {

    private final static String ROUTING_KEY = "async_protobuf";

    @Inject
    private Channel channel;

    private QueueingConsumer consumer;

    private void init() throws IOException {
        channel.queueDeclare(ROUTING_KEY, false, false, false, null);
        consumer = new QueueingConsumer(channel);
        channel.basicConsume(ROUTING_KEY, true, consumer);
    }

    public static void main(String[] args) throws Exception {
        ProtobufConsumer protobufConsumer = Guice.createInjector(new RabbitmqModule()).getInstance(ProtobufConsumer.class);
        protobufConsumer.init();

        while (true) {
            Delivery delivery = protobufConsumer.consumer.nextDelivery();

            Greeting greeting = Greeting.parseFrom(delivery.getBody());

            log.info("Receive " + greeting.getId() + " greeting from " + greeting.getFrom() + ", content : " + greeting.getContent());
        }
    }
}
