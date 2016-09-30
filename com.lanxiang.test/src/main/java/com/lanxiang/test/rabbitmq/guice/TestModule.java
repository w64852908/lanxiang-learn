package com.lanxiang.test.rabbitmq.guice;

import com.google.inject.PrivateModule;
import com.lanxiang.rabbitmq.async.consumer.AsyncConsumer;
import com.lanxiang.rabbitmq.async.producer.AsyncProducer;
import com.lanxiang.test.rabbitmq.annotation.ServiceConsumerAnnotation;
import com.lanxiang.test.rabbitmq.annotation.ServiceProducerAnnotation;
import com.lanxiang.test.rabbitmq.async.ServiceConsumer;
import com.lanxiang.test.rabbitmq.async.ServiceProducer;

/**
 * Created by lanxiang on 2016/9/28.
 */
public class TestModule extends PrivateModule {

    protected void configure() {
        bind(AsyncProducer.class).annotatedWith(ServiceProducerAnnotation.class).
                to(ServiceProducer.class).asEagerSingleton();

        bind(AsyncConsumer.class).annotatedWith(ServiceConsumerAnnotation.class).
                to(ServiceConsumer.class).asEagerSingleton();
    }
}
