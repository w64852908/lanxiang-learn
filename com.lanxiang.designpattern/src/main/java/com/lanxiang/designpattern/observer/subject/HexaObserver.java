package com.lanxiang.designpattern.observer.subject;

/**
 * Created by lanxiang on 2016/12/2.
 */
public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hexa String: " + Integer.toHexString(subject.getState()));
    }
}
