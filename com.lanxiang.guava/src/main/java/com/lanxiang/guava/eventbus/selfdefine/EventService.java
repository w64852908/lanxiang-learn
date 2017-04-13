package com.lanxiang.guava.eventbus.selfdefine;

import java.util.Date;

/**
 * Created by lanxiang on 2017/4/12.
 */
public class EventService {

    public EventService(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void handleEvent(HelloEvent event) {
        if (event == null) {
            return;
        }
        System.out.println("handleEvent received: " + event);
    }

    @Subscribe
    public void doHelloEvent(HelloEvent event) {
        if (event == null) {
            return;
        }
        System.out.println("doHelloEvent received: " + event);
    }

    @Subscribe
    public void doGoodByEvent(GoodByEvent event) {
        if (event == null) {
            return;
        }
        System.out.println("doGoodByEvent received: " + event.toString());
    }

    @Subscribe
    public void receiveGoodByEvent(GoodByEvent event) {
        if (event == null) {
            return;
        }
        System.out.println("receiveGoodByEvent received: " + event.toString());
    }
}

class HelloEvent {

    private String greeting;

    private Date date;

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HelloEvent{" +
                "greeting='" + greeting + '\'' +
                ", date=" + date +
                '}';
    }
}

class GoodByEvent {

    private String saying;

    private Date date;

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GoodByEvent{" +
                "saying='" + saying + '\'' +
                ", date=" + date +
                '}';
    }
}
