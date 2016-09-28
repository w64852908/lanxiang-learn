package com.lanxiang.rabbitmq.async.message;

import com.lanxiang.rabbitmq.async.producer.AbstractAsyncProducer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by lanxiang on 2016/9/28.
 */
@Slf4j
public abstract class RoutingAsyncProducer extends AbstractAsyncProducer {

    @Override
    protected String getType() {
        return "direct";
    }

    public RoutingAsyncProducer() {
        try {
            channel.exchangeDeclare(getExchangeName(), getType());
        }catch (IOException e){
            log.error("Declare channel exchange failed, " + e.getMessage());
        }
    }
}