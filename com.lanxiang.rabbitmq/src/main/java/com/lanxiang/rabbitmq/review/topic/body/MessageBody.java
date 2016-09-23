package com.lanxiang.rabbitmq.review.topic.body;

import java.io.Serializable;

/**
 * Created by lanxiang on 16/9/20.
 */
public class MessageBody implements Serializable {

    private Object object;

    private Class<?> clazz;

    private MessageBody() {

    }

    public MessageBody(Object object) {
        this.object = object;
        this.clazz = object.getClass();
    }

    public Object getObject() {
        return object;
    }

    public Class<?> getEvent() {
        return clazz;
    }
}