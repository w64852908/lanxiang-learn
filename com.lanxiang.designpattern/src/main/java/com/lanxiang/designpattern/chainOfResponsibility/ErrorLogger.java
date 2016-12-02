package com.lanxiang.designpattern.chainOfResponsibility;

/**
 * Created by lanxiang on 2016/12/2.
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
