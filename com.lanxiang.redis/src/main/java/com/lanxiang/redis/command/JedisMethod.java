package com.lanxiang.redis.command;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * Created by lanxiang on 2016/10/18.
 */
@Slf4j
public class JedisMethod {

    private JedisCluster jedisCluster;

    private Jedis jedis;

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new JedisModule());
//        jedisCluster = injector.getProvider(JedisCluster.class).get();
        jedis = injector.getProvider(JedisPool.class).get().getResource();
    }

    @Test
    public void testJedisCluster() {
        log.info(jedisCluster.toString());
    }

    @Test
    public void testJedis() {
        log.info(jedis.toString());
    }

    @Test
    public void testAppend() {
        String key = "testAppend";
        if (jedis.get(key) != null) {
            jedis.del(key);
        }
        for (int i = 0; i < 10; i++) {
            jedis.append(key, i + "");
        }
        log.info("get key \'testAppend\' : " + jedis.get(key));
    }

    @Test
    public void testRenamenx() {
        jedis.renamenx("testAppend", "testAppend1");
    }

    @Test
    public void testExpire() {
        jedis.expire("testAppend", 100);
    }

    @Test
    public void testIncr() {
        String key = "asset:714353:5860e50ded113579ca3b0fad";
        if (jedis.get(key) == null) {
            jedis.append(key, 0 + "");
        }
        int addNum = 10;
        System.out.println(jedis.get(key));
        jedis.incrBy(key, addNum);
        System.out.println(jedis.get(key));
    }
}
