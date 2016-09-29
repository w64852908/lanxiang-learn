package com.lanxiang.rabbitmq.async.producer;



/**
 * Created by lanxiang on 2016/9/28.
 */
public abstract class RoutingAsyncProducer extends AbstractAsyncProducer {

    @Override
    protected String getType() {
        return "direct";
    }

    @Override
    protected String getExchangeName() {
        return "ex_service_direct";
    }
}