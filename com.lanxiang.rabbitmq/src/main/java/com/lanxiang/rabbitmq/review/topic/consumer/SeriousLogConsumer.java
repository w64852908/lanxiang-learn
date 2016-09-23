package com.lanxiang.rabbitmq.review.topic.consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 16/9/19.
 */
public class SeriousLogConsumer extends MessageTopicConsumer {

    public SeriousLogConsumer() {
        super();
    }

    public List<String> getBindingKeys() {
        log.info("[*] Waiting for serious log. To exit press CTRL+C.");
        List<String> routingKeys = new ArrayList<String>();
        routingKeys.add("wbg.warn.log");
        routingKeys.add("wbg.error.log");
        return routingKeys;
    }

    public String getQueueName() {
        return "async.serious.log";
    }
}
