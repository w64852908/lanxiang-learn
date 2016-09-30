package com.lanxiang.test.rabbitmq.run;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.lanxiang.rabbitmq.async.guice.AsyncModule;
import com.lanxiang.rabbitmq.resource.guice.RabbitmqModule;
import com.lanxiang.test.rabbitmq.annotation.ServiceProducerAnnotation;
import com.lanxiang.test.rabbitmq.async.ServiceProducer;
import com.lanxiang.test.rabbitmq.event.Event;
import com.lanxiang.test.rabbitmq.event.PersistEvent;
import com.lanxiang.test.rabbitmq.event.RemindEvent;
import com.lanxiang.test.rabbitmq.guice.TestModule;

import java.util.*;

/**
 * Created by lanxiang on 2016/9/29.
 */
public class ProduceMessage {

    @ServiceProducerAnnotation
    @Inject
    private ServiceProducer serviceProducer;

    private final static Random random = new Random();

    private void inject() {
        Injector injector = Guice.createInjector(
                new RabbitmqModule(),
                new AsyncModule(),
                new TestModule());
        serviceProducer = injector.getInstance(ServiceProducer.class);
        serviceProducer.declareExchange();
    }

    private List<Event> generateMessages() {
        List<Event> messages = new ArrayList<Event>();
        for (int i = 0; i < 100; i++) {
            int r = random.nextInt(2);
            if (r == 1) {
                RemindEvent remindEvent = new RemindEvent();
                remindEvent.setNum(i);
                remindEvent.setTitle("提醒事项");
                remindEvent.setContent("起床了");
                remindEvent.setCreateAt(new Date());
                remindEvent.setReapted(true);
                remindEvent.setRepeatedDate(Arrays.asList("Monday", "Tuesday"));
                messages.add(remindEvent);
            } else {
                PersistEvent persistEvent = new PersistEvent();
                persistEvent.setNum(i);
                persistEvent.setId(UUID.randomUUID().toString());
                persistEvent.setCreateAt(new Date());
                persistEvent.setData("放国庆啦");
                messages.add(persistEvent);
            }
        }
        return messages;
    }

    public static void main(String[] args) {
        ProduceMessage produceMessage = new ProduceMessage();
        produceMessage.inject();

        List<Event> messages = produceMessage.generateMessages();

        for (Event event : messages) {
            produceMessage.serviceProducer.sendMessage(event);
        }
        System.exit(1);
    }
}
