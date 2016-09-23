package com.lanxiang.rabbitmq.review.topic;

import com.lanxiang.rabbitmq.review.topic.consumer.MessageConsumer;
import com.lanxiang.rabbitmq.review.topic.consumer.SlightLogConsumer;

/**
 * Created by lanxiang on 16/9/19.
 */
public class RunSlightLogConsumer {
    public static void main(String[] args) {
        MessageConsumer slightLogConsumer = new SlightLogConsumer();
    }
}
