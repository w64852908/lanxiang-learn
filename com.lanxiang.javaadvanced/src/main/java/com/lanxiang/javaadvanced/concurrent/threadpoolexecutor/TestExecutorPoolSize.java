package com.lanxiang.javaadvanced.concurrent.threadpoolexecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

/**
 * Created by lanxiang on 2018/11/28.
 */
public class TestExecutorPoolSize {

    @Test
    public void testCoreSizeZero() {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 2, 15,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 1; i++) {
            final int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("process : " + finalI + ", active thread : " + threadPoolExecutor.getActiveCount() +
                            ", queue size : " + threadPoolExecutor.getQueue().size());
                }
            });
        }
    }

    @Test
    public void testFuture() {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 2, 15,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10), new ThreadPoolExecutor.AbortPolicy());

        Future<String> future = threadPoolExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return System.currentTimeMillis() + "";
            }
        });
        System.out.println("task commit : " + System.currentTimeMillis());
        try {
            String res = future.get(5, TimeUnit.SECONDS);
            System.out.println("task complete : " + res);

            res = future.get(2, TimeUnit.SECONDS);
            System.out.println("task complete2 : " + res);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
