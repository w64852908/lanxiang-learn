package com.lanxiang.jvm.volatileword;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lanxiang on 2017/2/21.
 */
public class ThreadOrder {

    private static int race = 0;

    private static List<Integer> list = new ArrayList<>();

    //重入锁
    private final static Lock lock = new ReentrantLock(true);

    //普通自增方法
    private void increase() {
        for (int i = 0; i < 100; i++) {
            list.add(race);
            race++;
        }
    }

    //用公平的重入锁加锁
    private void fairIncrease(int retry) {
        if (lock.tryLock()) {
            try {
                for (int i = 0; i < 100; i++) {
                    list.add(race);
                    race++;
                }
            } finally {
                lock.unlock();
            }
        } else {
            if (retry >= 3){
                throw new RuntimeException("Get fair lock failed.");
            }
            fairIncrease(++retry);
        }
    }

    //检查list是否有序
    private void checkListOrder(List<Integer> list) {
        int n = 0;
        for (Integer i : list) {
            if (i != n) {
                throw new RuntimeException("线程不是顺序执行的");
            }
            n++;
        }
        System.out.println("线程是按照顺序执行的");
    }

    //普通的线程
    @Test
    public void normalThread() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increase();
                }
            }).start();
        }
        Thread.sleep(5000);
        checkListOrder(list);
    }

    //使用join保证有序
    @Test
    public void joinThread() throws Exception {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    increase();
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
            thread.join();
        }
        Thread.sleep(5000);
        checkListOrder(list);
    }

    //使用单个线程池保证有序
    @Test
    public void singleThreadPoolThread() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    increase();
                }
            });
        }
        Thread.sleep(5000);
        checkListOrder(list);
    }

    //可重入锁的fair属性
    @Test
    public void reentrantLockThread() throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    fairIncrease(0);
                }
            });
        }
        Thread.sleep(5000);
        checkListOrder(list);
    }
}
