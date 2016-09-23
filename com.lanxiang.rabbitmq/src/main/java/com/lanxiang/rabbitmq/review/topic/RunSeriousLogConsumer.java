package com.lanxiang.rabbitmq.review.topic;

import com.lanxiang.rabbitmq.review.topic.consumer.MessageConsumer;
import com.lanxiang.rabbitmq.review.topic.consumer.SeriousLogConsumer;

/**
 * Created by lanxiang on 16/9/19.
 */
public class RunSeriousLogConsumer {

    public static void main(String[] args) {
        MessageConsumer seriousLogConsumer = new SeriousLogConsumer();
    }
}
