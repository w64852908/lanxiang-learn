package com.lanxiang.jvm.volatileword;

import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by lanxiang on 2017/2/20.
 */
public class VolatileTest {

    private static volatile int race = 0;

    private final static Object curr = new Object();

    private final static Object next = new Object();

    public static void increase() {
        synchronized (curr) {
            for (int i = 0; i < 10000; i++) {
                System.out.println(race);
                race++;
            }
        }
    }

    private final static int THREAD_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("thread");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute((new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread : " + Thread.currentThread().getName() + " start to print.");
                    increase();
                    System.out.println("Thread : " + Thread.currentThread().getName() + " finished to print.");
                    System.out.println();
                }
            }));
        }
        stopWatch.stop();
        Thread.sleep(5000);
        System.out.println(race);
        System.out.println(stopWatch.prettyPrint());
    }
}
