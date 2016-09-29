package com.lanxiang.rabbitmq.async.producer;

/**
 * Created by lanxiang on 2016/9/26.
 */
public interface AsyncProducer {

    //发送持久化的消息
    void sendMessage(Object body);

    //发送带有选择键的持久化消息
    void sendMessage(Object body, String routingKey);
}
