package com.lanxiang.javaadvanced.concurrent.threadlocal;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/17.
 */
public class MyThreadLocal extends ThreadLocal<Integer> {

    @Override
    protected Integer initialValue() {
        return 10086;
    }

    @Test
    public void run() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new MyThreadLocal().get());
                }
            }).start();
        }

    }
}
