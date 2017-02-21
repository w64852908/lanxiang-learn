package com.lanxiang.jvm.outofmemory;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2017/2/17.
 */
public class OutOfMemoryTest {

    @Before
    public void init() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    static class OOMObject {

    }

    @Test
    public void heapOOM() {

        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }

    private void loop(int deep) {
        System.out.println(deep++);
        loop(deep);
    }

    @Test
    public void stackLeak() {
        int deep = 1;
        try {
            loop(deep);
        } catch (Throwable t) {
            throw t;
        }

    }

    private void dontStop() {
        System.out.println("Create thread : " + Thread.currentThread().getName());
        while (true) {

        }
    }

    @Test
    public void stackLeakThread() {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            }).start();
        }
    }
    
}
