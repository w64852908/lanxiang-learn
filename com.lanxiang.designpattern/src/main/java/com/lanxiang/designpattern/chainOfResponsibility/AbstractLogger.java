package com.lanxiang.designpattern.chainOfResponsibility;

/**
 * Created by lanxiang on 2016/12/2.
 */
public abstract class AbstractLogger {

    public static int INFO = 1;

    public static int DEBUG = 2;

    public static int ERROR = 3;

    protected int level;

    //责任链的下一个元素(链表的感觉)
    protected AbstractLogger nextLogger;

    public AbstractLogger setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
        return nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract void write(String message);
}
