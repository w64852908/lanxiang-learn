package com.lanxiang.guava.eventbus.demo;

import com.google.common.eventbus.AsyncEventBus;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * Created by lanxiang on 16/8/29.
 */
public class Demo2 {

    @Test
    public void testAysncEventBus() {
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
        eventBus.register(new Event());
        String message = "I have sent ";
        for (int i = 0; i < 100; i++) {
            eventBus.post(message + i + " numbers.");
        }
        System.out.println("============");
    }
}
