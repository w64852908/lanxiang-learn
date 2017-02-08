package com.lanxiang.mybatis.model;


import lombok.Data;

@Data
public class User {

    private Integer id;

    private String name;

    private Integer sex;

    public User(){}

    public User(Integer id, String name, Integer sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}