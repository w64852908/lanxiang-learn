package com.lanxiang.designpattern.observer;

/**
 * Created by lanxiang on 2016/12/2.
 */
public abstract class Observer {

    protected Subject subject;

    public abstract void update();
}
