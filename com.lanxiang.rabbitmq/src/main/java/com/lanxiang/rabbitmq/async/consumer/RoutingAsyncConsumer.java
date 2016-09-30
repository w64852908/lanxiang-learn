package com.lanxiang.rabbitmq.async.consumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lanxiang on 2016/9/30.
 */
public class RoutingAsyncConsumer extends AbstractAsyncConsumer {

    @Override
    protected String getExchangeName() {
        return "ex_service_direct";
    }

    @Override
    protected String getType() {
        return "direct";
    }

    @Override
    protected String getQueueName() throws IOException {
        return "async.service.queue";
    }

    @Override
    protected List<String> getBindingKeys() {
        return Arrays.asList("async_service");
    }
}