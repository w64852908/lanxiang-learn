package com.lanxiang.exercise.designpattern.singleton;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/13.
 */
public class HelloUtilTestCase {

    @Test
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(HelloUtil.getInstance());
        }
    }
}
