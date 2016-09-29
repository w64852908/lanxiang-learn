package com.lanxiang.test.rabbitmq.async;


import com.lanxiang.rabbitmq.async.producer.RoutingAsyncProducer;
import com.lanxiang.rabbitmq.async.producer.AsyncProducer;

/**
 * Created by lanxiang on 2016/9/28.
 */
public class ServiceProducer extends RoutingAsyncProducer implements AsyncProducer{

    @Override
    protected String getDefaultRoutingKey() {
        return "async_service";
    }
}