package com.lanxiang.designpattern.singleton;

import org.junit.Test;

/**
 * Created by lanxiang on 2016/12/2.
 */
public class SingletonDemo {

    @Test
    public void run() {
        Singleton6 singleton6 = Singleton6.INSTANCE;
        singleton6.whateverMethod();
    }
}
