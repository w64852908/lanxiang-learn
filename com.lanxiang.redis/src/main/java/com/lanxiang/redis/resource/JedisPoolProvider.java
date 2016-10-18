package com.lanxiang.redis.resource;

import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by lanxiang on 2016/10/17.
 */
@Slf4j
public class JedisPoolProvider implements Provider<JedisPool> {

    private JedisPool jedisPool;

    private void initJedisPool() {
        if (jedisPool == null) {
            ResourceBundle rb = ResourceBundle.getBundle("redis-cfg");
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMinIdle(Integer.valueOf(rb.getString("pool.minIdle")));
            config.setMaxIdle(Integer.valueOf(rb.getString("pool.maxIdle")));
            config.setMaxWaitMillis(Integer.valueOf(rb.getString("pool.maxWait")));
            config.setMaxTotal(Integer.valueOf(rb.getString("pool.maxTotal")));
            config.setTestOnBorrow(Boolean.valueOf(rb.getString("pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(rb.getString("pool.testOnReturn")));
            config.setTimeBetweenEvictionRunsMillis(60*1000);
            jedisPool = new JedisPool(config, rb.getString("server.host"), Integer.valueOf(rb.getString("server.port")));
        }
    }

    @Override
    public JedisPool get() {
        initJedisPool();
        return jedisPool;
    }
}
