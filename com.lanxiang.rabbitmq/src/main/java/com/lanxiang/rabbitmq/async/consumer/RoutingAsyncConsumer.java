package com.lanxiang.rabbitmq.async.consumer;

/**
 * Created by lanxiang on 2016/9/30.
 */
public abstract class RoutingAsyncConsumer extends AbstractAsyncConsumer {

    @Override
    protected String getExchangeName() {
        return "ex_service_direct";
    }

    @Override
    protected String getType() {
        return "direct";
    }

}