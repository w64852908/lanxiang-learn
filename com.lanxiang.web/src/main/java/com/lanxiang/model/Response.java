package com.lanxiang.model;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by lanxiang on 16/9/2.
 */

@XmlRootElement
@XmlSeeAlso({User.class})
public class Response {


    private int status;

    private String message;

    private Object data;

    public Response setStatus(int status) {
        this.status = status;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
