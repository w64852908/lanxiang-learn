package com.lanxiang.javaadvanced.concurrent;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lanjing on 2017/3/26.
 */
public class ThreadLocalTest {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void run() {
        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(format.format(new Date()));
                }
            }).start();
        }
    }
}
