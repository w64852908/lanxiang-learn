package com.lanxiang.javaadvanced.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lanjing on 2017/2/24.
 */
public class CountDownLatchTest {

    @Test
    public void run1() throws InterruptedException {
        //开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);
        //结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);
        //十名选手
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++){
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //如果当前计数器为0,则此方法立即返回
                        //等待
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + no + " arrived.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        end.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        System.out.println("Game start");
        //begin减一,开始游戏
        begin.countDown();
        //等待wait变成0,即所有选手到达终点
        end.await();
        System.out.println("Game over");
        executorService.shutdown();
    }
}
