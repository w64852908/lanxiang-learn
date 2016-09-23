package com.lanxiang.morphia.test;

import com.lanxiang.morphia.model.Concurrent;
import org.junit.Test;

/**
 * Created by lanxiang on 16/9/21.
 */
public class TestConcurrentClass {
    @Test
    public void test1() {
        long num = 10086;
        System.out.println(new Concurrent(num).toString());
    }
}
