package com.lanxiang.redis.redisInAction.performance;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.IOException;

/**
 * Created by lanxiang on 2017/2/15.
 */
public class PerformanceTest {

    private Jedis jedis;

    private final static int testNum = 400000;

    private final static String sKey = "sSpeed:";

    private final static String hKey = "hSpeed:";

    private final static int step = 100;

    private int progress;

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new JedisModule());
        jedis = injector.getProvider(JedisPool.class).get().getResource();
        progress = testNum / step;
    }

    //普通方式
    private void normal(Jedis jedis) {
        for (int i = 1; i <= testNum; i++) {
            String item = "num" + i;
            jedis.sadd(sKey, "num" + i);
            jedis.hset(hKey, item, i + "");
            if (i % progress == 0) {
                System.out.println("Execute add : " + i + " / " + testNum);
            }
        }
    }

    //使用流水线
    private void pipe(Jedis jedis) {
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        for (int i = 1; i <= testNum; i++) {
            String item = "num" + i;
            pipeline.sadd(sKey, "num" + i);
            pipeline.hset(hKey, item, i + "");
        }
        pipeline.exec();
        try {
            pipeline.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clean(Jedis jedis) {
        for (int i = 1; i <= testNum; i++) {
            String item = "num" + i;
            jedis.srem(sKey, "num" + i);
            jedis.hdel(hKey, item);
            if (i % progress == 0) {
                System.out.println("Execute clean : " + i + " / " + testNum);
            }
        }
    }

    @Test
    public void run() {
        clean(jedis);
        StopWatch stopWatch = new StopWatch("Performance");
        stopWatch.start("normal");
        normal(jedis);
        stopWatch.stop();
        stopWatch.start("clean");
        clean(jedis);
        stopWatch.stop();
        stopWatch.start("pipe");
        pipe(jedis);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void clean() {
        clean(jedis);
    }
}
