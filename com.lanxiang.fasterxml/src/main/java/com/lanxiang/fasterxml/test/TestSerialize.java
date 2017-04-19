package com.lanxiang.fasterxml.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.util.ObjectMapperFactory;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanjing on 2017/4/16.
 */
public class TestSerialize {

    private ObjectMapper objectMapper;

    @Before
    public void init() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    @Test
    public void run() throws IOException, InterruptedException {
        ObjectId objectId = new ObjectId();
        System.out.println(objectId);
        byte[] bytes = objectMapper.writeValueAsBytes(objectId);
        TimeUnit.SECONDS.sleep(2);
        System.out.println(objectMapper.readValue(bytes, ObjectId.class));
    }

    @Test
    public void run1() throws Exception {
        User user = new User();
        user.setId(10086);
        user.setName("lanxiang");
        System.out.println(user);
        byte[] bytes = objectMapper.writeValueAsBytes(user);
        TimeUnit.SECONDS.sleep(2);
        System.out.println(objectMapper.readValue(bytes, User.class));
    }
}

class User {

    private Integer id;

    private String name;

    private Date date;

    public User() {
        this.date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
