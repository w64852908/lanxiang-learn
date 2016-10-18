package com.lanxiang.redis.command;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * Created by lanxiang on 2016/10/18.
 */
@Slf4j
public class JedisClusterMethod {

    private JedisCluster jedisCluster;

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new JedisModule());
        jedisCluster = injector.getProvider(JedisCluster.class).get();
    }

    @Test
    public void test1(){
        log.info(jedisCluster.toString());
    }
}
