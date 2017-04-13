package com.lanxiang.guava.eventbus.selfdefine;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;


/**
 * Created by lanxiang on 2017/4/12.
 */
public class UnitTest {

    private EventBus eventBus;

    private EventService eventService;

    @Before
    public void init() {
        eventBus = new EventBus();
        eventService = new EventService(eventBus);
    }

    private void postSeveralEvent() {
        HelloEvent helloEvent = new HelloEvent();
        helloEvent.setGreeting("你好啊");
        helloEvent.setDate(new Date());

        GoodByEvent goodByEvent = new GoodByEvent();
        goodByEvent.setSaying("再见啦");
        goodByEvent.setDate(new Date());

        eventBus.post(helloEvent);
        eventBus.post(goodByEvent);
    }

    @Test
    public void run() throws Exception {
        postSeveralEvent();
        while (Thread.activeCount() > 2) {

        }
    }
}
