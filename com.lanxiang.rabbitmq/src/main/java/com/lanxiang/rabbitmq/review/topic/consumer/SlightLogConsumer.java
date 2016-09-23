package com.lanxiang.rabbitmq.review.topic.consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 16/9/19.
 */
public class SlightLogConsumer extends MessageTopicConsumer {

    public SlightLogConsumer() {
        super();
    }

    public List<String> getBindingKeys() {
        log.info("[*] Waiting for slight log. To exit press CTRL+C.");
        List<String> routingKeys = new ArrayList<String>();
        routingKeys.add("wbg.info.log");
        routingKeys.add("wbg.debug.log");
        return routingKeys;
    }

    public String getQueueName() {
        return "async.slight.log";
    }
}
