package com.lanxiang.designpattern.singleton;

import org.junit.Test;

/**
 * Created by lanjing on 2017/2/19.
 */
public class EnumSingletonRun {

    @Test
    public void run() {
        System.out.println(EnumSingleton1.INSTANCE.getValue());
    }
}
