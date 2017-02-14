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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2017/2/14.
 */
public class Transaction {

//    private Jedis jedis;

    private final static String noTransKey = "notrans:";

    private final static String transKey = "trans:";

    private ExecutorService executorService;

    private Injector injector;

    @Before
    public void init() {
        injector = Guice.createInjector(new JedisModule());
        executorService = Executors.newFixedThreadPool(3);
    }

    private void noTransaction(Jedis jedis) throws Exception {
        //对计数器自增并打印操作的执行结果
        System.out.println(jedis.incr(noTransKey));
        TimeUnit.MILLISECONDS.sleep(100);
        //自减
        jedis.incrBy(noTransKey, -1);
    }

    @Test
    public void runNoTransaction() throws Exception {
        int i = 0;
        //启动三个线程来执行没有被事务包裹的自增 休眠 自减操作
        while (i < 3) {
            i++;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Jedis jedis = injector.getProvider(JedisPool.class).get().getResource();
                        noTransaction(jedis);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        TimeUnit.MILLISECONDS.sleep(500);
    }

    private void transaction(Pipeline pipeline) throws Exception {
        pipeline.multi();
        pipeline.incr(transKey);
        TimeUnit.MILLISECONDS.sleep(100);
        pipeline.incrBy(transKey, -1);
        System.out.println(pipeline.syncAndReturnAll().get(0));
    }

    @Test
    public void runTransaction() throws Exception {
        int i = 0;
        while (i < 3) {
            i++;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Jedis jedis = injector.getProvider(JedisPool.class).get().getResource();
                        final Pipeline pipeline = jedis.pipelined();
                        transaction(pipeline);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
