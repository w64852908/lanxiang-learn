package com.lanxiang.test.rabbitmq.async;


import com.lanxiang.rabbitmq.async.message.RoutingAsyncProducer;

/**
 * Created by lanxiang on 2016/9/28.
 */
public class ServiceProducer extends RoutingAsyncProducer {

    protected String getExchangeName() {
        return "ex_service_direct";
    }
}