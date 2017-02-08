package com.lanxiang.javaadvanced.compare;

import lombok.Data;

import java.util.Date;

/**
 * Created by lanxiang on 2016/12/2.
 */
@Data
public class User{

    private int id;

    private String name;

    private Date date;

    public User(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }
}
