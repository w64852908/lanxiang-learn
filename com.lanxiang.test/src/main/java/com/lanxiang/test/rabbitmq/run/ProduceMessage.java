package com.lanxiang.test.rabbitmq.run;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.lanxiang.rabbitmq.async.guice.AsyncModule;
import com.lanxiang.rabbitmq.resource.guice.RabbitmqModule;
import com.lanxiang.test.rabbitmq.annotation.ServiceProducerAnnotation;
import com.lanxiang.test.rabbitmq.async.ServiceProducer;
import com.lanxiang.test.rabbitmq.event.RemindEvent;
import com.lanxiang.test.rabbitmq.guice.TestModule;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by lanxiang on 2016/9/29.
 */
public class ProduceMessage {

    @ServiceProducerAnnotation
    @Inject
    private ServiceProducer serviceProducer;

    private void inject() {
        Injector injector = Guice.createInjector(
                new RabbitmqModule(),
                new AsyncModule(),
                new TestModule());
        serviceProducer = injector.getInstance(ServiceProducer.class);
        serviceProducer.declareExchange();
    }

    public static void main(String[] args) {
        ProduceMessage produceMessage = new ProduceMessage();
        produceMessage.inject();
        RemindEvent remindEvent = new RemindEvent();
        remindEvent.setTitle("提醒事项");
        remindEvent.setContent("起床了");
        remindEvent.setCreateAt(new Date());
        remindEvent.setReapted(true);
        remindEvent.setRepeatedDate(Arrays.asList("Monday", "Tuesday"));
        produceMessage.serviceProducer.sendMessage(remindEvent);
    }
}
