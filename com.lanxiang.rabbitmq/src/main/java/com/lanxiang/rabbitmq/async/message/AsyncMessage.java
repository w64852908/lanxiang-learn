package com.lanxiang.rabbitmq.async.message;

import java.io.Serializable;

/**
 * Created by lanxiang on 2016/9/26.
 */
public class AsyncMessage implements Serializable {

    //消息的内容
    private Object object;

    //消息的class
    private Class<?> clazz;

    public AsyncMessage(Object object) {
        this.object = object;
        this.clazz = object.getClass();
    }

    public Object getObject() {
        return object;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "AsyncMessage{" +
                "object=" + object +
                ", clazz=" + clazz +
                '}';
    }
}