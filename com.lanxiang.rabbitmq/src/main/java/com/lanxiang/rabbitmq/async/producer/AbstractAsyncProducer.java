package com.lanxiang.rabbitmq.async.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.lanxiang.rabbitmq.async.message.AsyncMessage;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.io.IOException;

/**
 * Created by lanxiang on 2016/9/26.
 */
@Slf4j
@Singleton
public abstract class AbstractAsyncProducer {

    @Inject
    private Channel channel;

    @Inject
    private ObjectMapper objectMapper;

    //转发器
    protected abstract String getExchangeName();

    //channel的类型
    protected abstract String getType();

    protected abstract String getDefaultRoutingKey();

    public void declareExchange() {
        try {
            channel.exchangeDeclare(getExchangeName(), getType(), true, false, null);
        } catch (IOException e) {
            log.error("Declare channel exchange failed, " + e);
        }
    }

    private byte[] buildMessage(AsyncMessage asyncMessage) {
        byte[] bytes = null;
        try {
            bytes = objectMapper.writeValueAsBytes(asyncMessage);
        } catch (JsonProcessingException e) {
            log.error("Async message convert to bytes failed, " + e.getMessage());
        }
        return bytes;
    }

    public void sendMessage(Object object) {
        if (object == null) {
            return;
        }
        sendMessage(object, getDefaultRoutingKey());
    }

    public void sendMessage(Object object, String routingKey) {
        if (object == null) {
            return;
        }
        sendMessage(new AsyncMessage(object), routingKey);
    }

    private void sendMessage(AsyncMessage asyncMessage, String routingKey) {
        sendMessage(asyncMessage, MessageProperties.PERSISTENT_TEXT_PLAIN, routingKey);
    }

    private void sendMessage(AsyncMessage asyncMessage, AMQP.BasicProperties properties, String routingKey) {
        if (asyncMessage == null) {
            return;
        }
        log.info(" [x] Sent message (" + asyncMessage.toString() + ") to routingkey (" + routingKey + ")");
        byte[] bytes = buildMessage(asyncMessage);
        try {
            channel.basicPublish(getExchangeName(), routingKey, properties, bytes);
        } catch (IOException e) {
            log.error("Sent message failed, " + e.getMessage());
        }
    }
}