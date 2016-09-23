package com.lanxiang.rabbitmq.review.topic.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lanxiang on 16/9/20.
 */
public class TopicSubscriber {

    private final static Logger log = LoggerFactory.getLogger(TopicSubscriber.class);

    public void consume(Object object) {
        log.info("==============Subscribe message : " + object.toString() + " =================");
    }
}