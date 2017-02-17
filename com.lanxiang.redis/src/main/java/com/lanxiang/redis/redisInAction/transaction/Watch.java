package com.lanxiang.redis.redisInAction.transaction;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2017/2/16.
 */
public class Watch {

    private Injector injector;

    private ExecutorService executorService;

    private final static String key = "watch:";

    @Before
    public void init() {
        injector = Guice.createInjector(new JedisModule());
        executorService = Executors.newFixedThreadPool(2);
    }

    //对一个资源进行watch和unwatch操作
    private boolean watchAndUnwatch(Jedis jedis) {
        jedis.set(key, "lanxiang");
        jedis.expire(key, 40);
        Pipeline pipeline = jedis.pipelined();
        try {
            pipeline.watch(key);
            System.out.println(Thread.currentThread().getName() + "--watch key--" + key);
            pipeline.multi();
            pipeline.append(key, " is handsome");
            TimeUnit.SECONDS.sleep(10);
            Response<List<Object>> response = pipeline.exec();
            return response != null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pipeline.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //去修改被另一个jedis客户端watch的值
    private void updateWatchValue(Jedis jedis) throws Exception {
        Pipeline pipeline = jedis.pipelined();
        TimeUnit.SECONDS.sleep(5);
        pipeline.multi();
        pipeline.append(key, " is not handsome.");
        pipeline.exec();
        pipeline.close();
        System.out.println(Thread.currentThread().getName() + " update " + key);
        System.out.println("watch: value is " + jedis.get(key));
    }

    @Test
    public void run() throws InterruptedException {
        final Jedis jedis1 = injector.getProvider(JedisPool.class).get().getResource();
        final Jedis jedis2 = injector.getProvider(JedisPool.class).get().getResource();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (watchAndUnwatch(jedis2)) {
                    System.out.println("Succeed");
                } else {
                    System.out.println("failed");
                }
                System.out.println(jedis2.get(key));
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    updateWatchValue(jedis1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        TimeUnit.SECONDS.sleep(20);
    }
}