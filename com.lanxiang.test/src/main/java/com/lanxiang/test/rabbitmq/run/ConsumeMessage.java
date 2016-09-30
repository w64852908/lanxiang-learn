package com.lanxiang.test.rabbitmq.run;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.lanxiang.rabbitmq.async.annotation.AsyncMark;
import com.lanxiang.rabbitmq.async.guice.AsyncModule;
import com.lanxiang.rabbitmq.async.register.AsyncRegister;
import com.lanxiang.rabbitmq.resource.guice.RabbitmqModule;
import com.lanxiang.test.rabbitmq.async.ServiceConsumer;
import com.lanxiang.test.rabbitmq.event.PersistEvent;
import com.lanxiang.test.rabbitmq.event.RemindEvent;
import com.lanxiang.test.rabbitmq.guice.TestModule;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lanxiang on 2016/9/30.
 */
@Slf4j
public class ConsumeMessage {

    @Inject
    private AsyncRegister asyncRegister;

    @Inject
    private ServiceConsumer serviceConsumer;

    private void init() {
        Injector injector = Guice.createInjector(
                new RabbitmqModule(),
                new AsyncModule(),
                new TestModule());
        asyncRegister = injector.getInstance(AsyncRegister.class);
        asyncRegister.register(this);
        serviceConsumer = injector.getInstance(ServiceConsumer.class);
    }

    @AsyncMark
    public void showRemindEvent(RemindEvent remindEvent) throws Exception {
        log.info("Subscribe " + remindEvent.toString());
        Thread.sleep(500);
    }

    @AsyncMark
    public void showPersistEvent(PersistEvent persistEvent) throws Exception {
        log.info("Subscribe " + persistEvent.toString());
        Thread.sleep(500);
    }

    public static void main(String[] args) {
        ConsumeMessage consumeMessage = new ConsumeMessage();
        consumeMessage.init();
        consumeMessage.serviceConsumer.initConsumer();
    }
}
