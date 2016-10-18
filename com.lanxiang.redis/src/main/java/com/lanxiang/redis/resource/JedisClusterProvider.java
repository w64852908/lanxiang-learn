package com.lanxiang.redis.resource;

import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.ResourceBundle;

/**
 * Created by lanxiang on 2016/10/17.
 */
@Slf4j
public class JedisClusterProvider implements Provider<JedisCluster> {

    private JedisCluster jedisCluster;

    private void initJedisCluster() {
        if (jedisCluster == null) {
            ResourceBundle rb = ResourceBundle.getBundle("redis-cfg");

            HostAndPort hostAndPort = new HostAndPort(rb.getString("server.host"),
                    Integer.valueOf(rb.getString("server.port")));
            int time_out = Integer.valueOf(rb.getString("time_out"));
            int max_redirections = Integer.valueOf(rb.getString("max_redirections"));
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setTestOnBorrow(true);

            jedisCluster = new JedisCluster(hostAndPort, time_out, max_redirections, poolConfig);
        }
    }

    @Override
    public JedisCluster get() {
        initJedisCluster();
        return jedisCluster;
    }
}
