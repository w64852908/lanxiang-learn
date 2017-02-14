package com.lanxiang.redis.redisInAction.subandpub;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2017/2/14.
 */
public class SubAndPub {

    private Jedis jedis;

    private Jedis subJedis;

    private final static String channel = "channel";

    private ExecutorService executorService;

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new JedisModule());
        jedis = injector.getProvider(JedisPool.class).get().getResource();
        subJedis = injector.getProvider(JedisPool.class).get().getResource();
        executorService = Executors.newSingleThreadExecutor();
    }

    //发布消息
    private void publisher(Jedis jedis, List<String> msgList) throws Exception {
        //等订阅者创建完毕
        TimeUnit.SECONDS.sleep(1);
        for (String msg : msgList) {
            jedis.publish(channel, msg);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private void subscribe() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    publisher(jedis, Arrays.asList("1", "2", "3", "4", "5"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Subscriber subscriber = new Subscriber();
        subJedis.subscribe(subscriber, channel);
    }

    @Test
    public void run() {
        subscribe();
    }
}
