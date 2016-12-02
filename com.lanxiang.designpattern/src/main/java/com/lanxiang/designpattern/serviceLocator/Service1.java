package com.lanxiang.designpattern.serviceLocator;

/**
 * Created by lanxiang on 2016/12/1.
 */
public class Service1 implements Service{
    @Override
    public String getName() {
        return "Service1";
    }

    @Override
    public void execute() {
        System.out.println("Executing Service1");
    }
}
