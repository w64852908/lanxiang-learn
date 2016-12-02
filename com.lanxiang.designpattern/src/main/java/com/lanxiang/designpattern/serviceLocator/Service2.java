package com.lanxiang.designpattern.serviceLocator;

/**
 * Created by lanxiang on 2016/12/1.
 */
public class Service2 implements Service{
    @Override
    public String getName() {
        return "Service2";
    }

    @Override
    public void execute() {
        System.out.println("Executing service2");
    }
}
