package com.lanxiang.guice.annotationinterceptor.service;

import com.lanxiang.guice.annotationinterceptor.annotation.SystemOut;

/**
 * Created by lanxiang on 16/9/18.
 */
public class RunServiceImpl implements RunService{

    @SystemOut
    public void eat() {
        System.out.println("Eat some food.");
    }

    @SystemOut
    public void sleep() {
        System.out.println("Sleep for 8 hours.");
    }

    @SystemOut
    public void work() {
        System.out.println("Work for whole day.");
    }
}
