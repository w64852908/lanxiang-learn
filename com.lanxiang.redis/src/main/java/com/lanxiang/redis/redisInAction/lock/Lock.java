package com.lanxiang.redis.redisInAction.lock;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2017/2/15.
 */
public class Lock {

    private final static String key = "lock:";

    private Injector injector;

    private ExecutorService executorService;

    @Before
    public void init() {
        injector = Guice.createInjector(new JedisModule());
        executorService = Executors.newFixedThreadPool(5);
    }

    //尝试获取锁
    private String acquireLock(Jedis jedis, String lockName) {
        String identifier = UUID.randomUUID().toString();
        //如果获取到当前锁,给锁加一个过期时间
        if (jedis.setnx(key + lockName, identifier) == 1) {
            jedis.expire(key + lockName, 1);
            return identifier;
        } else {
            return null;
        }
    }

    private void tryToGetLock(Jedis jedis, String lockName) {
        String lockId = acquireLock(jedis, lockName);
        //如果没有获取到锁,自旋
        while (lockId == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockId = acquireLock(jedis, lockName);
        }
        System.out.println(Thread.currentThread().getName() + ", 获取到了锁, " + lockId + ", at " + new Date().getTime());
    }

    @Test
    public void run() throws InterruptedException {
        final String lockName = "lanxiang";
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = injector.getProvider(JedisPool.class).get().getResource();
                    tryToGetLock(jedis, lockName);
                }
            });
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
