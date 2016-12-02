package com.lanxiang.designpattern.chainOfResponsibility;


/**
 * Created by lanxiang on 2016/12/2.
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File Console::Logger: " + message);
    }
}
