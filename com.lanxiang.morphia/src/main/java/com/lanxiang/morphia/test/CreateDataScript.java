package com.lanxiang.morphia.test;

import com.google.inject.Guice;
import com.lanxiang.morphia.module.MongoModule;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

/**
 * Created by lanxiang on 2017/4/14.
 */
public class CreateDataScript {


    private Datastore datastore;

    @Before
    public void init() {
        datastore = Guice.createInjector(new MongoModule()).getInstance(Datastore.class);
    }

    @Test
    public void run() {
        for (int i = 0; i < 2000000; i++) {
            datastore.save(new User(i, "zhangsan" + i, (i + 10) % 100));
        }
    }
}

class User {

    private int index;

    private String name;

    private int age;

    public User(int index, String name, int age) {
        this.index = index;
        this.name = name;
        this.age = age;
    }
}
