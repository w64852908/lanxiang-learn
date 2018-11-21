package com.lanxiang.javaadvanced.concurrent.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2018/11/13.
 */
public class FutureCancelTest {

    private Semaphore semaphore = new Semaphore(0);

    private ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));

    private Future<String> future;

    public void testFuture() {
        future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String result = null;
                try {
                    semaphore.acquire();
                    result = "ok";
                } catch (InterruptedException e) {
                    result = "interrupted";
                    System.out.println("acquire semaphore interrupted.");
                }
                return result;
            }
        });


        String result = "timeout";
        try {
            result = future.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("超时异常");
            System.out.println("future execute timeout.");

        }

        // 删除线程池中任务
        boolean cancelResult = future.cancel(true);

        System.out.println("result is " + result);
        System.out.println("删除结果：" + cancelResult);
        System.out.println("当前active线程数：" + pool.getActiveCount());
    }

    public static void main(String[] args) {
        FutureCancelTest o = new FutureCancelTest();
        o.testFuture();
    }
}