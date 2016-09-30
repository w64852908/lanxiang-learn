package com.lanxiang.test.rabbitmq.event;


import java.util.Date;

/**
 * Created by lanxiang on 2016/9/30.
 */
public class PersistEvent extends Event {

    private String id;

    private String data;

    private Date createAt;

    public void setId(String id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public Date getCreateAt() {
        return createAt;
    }

    @Override
    public String toString() {
        return "PersistEvent{" +
                "num='" + num + '\'' +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
