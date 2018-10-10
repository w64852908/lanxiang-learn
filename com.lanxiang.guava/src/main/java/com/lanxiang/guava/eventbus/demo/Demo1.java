package com.lanxiang.guava.eventbus.demo;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

/**
 * Created by lanxiang on 16/8/29.
 */
public class Demo1 {

    @Test
    public void testEventBus(){
        EventBus eventBus = new EventBus();
        eventBus.register(new EventService());
        eventBus.post("lanxiang ooooooo~~~~~");
    }
}


class EventService {

    @Subscribe
    public void sub(String message) {
        System.out.println(this.toString() + " : " +message);
    }

}
