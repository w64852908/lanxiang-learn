package com.lanxiang.test.rabbitmq.async;

import com.lanxiang.rabbitmq.async.consumer.AsyncConsumer;
import com.lanxiang.rabbitmq.async.consumer.RoutingAsyncConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lanxiang on 2016/9/30.
 */
public class ServiceConsumer extends RoutingAsyncConsumer implements AsyncConsumer{

    @Override
    protected String getQueueName() throws IOException {
        return "async.service.queue";
    }

    @Override
    protected List<String> getBindingKeys() {
        return Arrays.asList("async_service");
    }
}
