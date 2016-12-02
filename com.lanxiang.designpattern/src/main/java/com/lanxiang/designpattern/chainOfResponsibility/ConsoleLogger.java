package com.lanxiang.designpattern.chainOfResponsibility;

/**
 * Created by lanxiang on 2016/12/2.
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
