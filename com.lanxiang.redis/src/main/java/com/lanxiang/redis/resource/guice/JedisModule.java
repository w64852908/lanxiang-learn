package com.lanxiang.redis.resource.guice;

import com.google.inject.PrivateModule;
import com.lanxiang.redis.resource.JedisClusterProvider;
import com.lanxiang.redis.resource.JedisPoolProvider;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * Created by lanxiang on 2016/10/17.
 */
public class JedisModule extends PrivateModule {

    @Override
    protected void configure() {
        bind(JedisPool.class).toProvider(JedisPoolProvider.class).asEagerSingleton();
        expose(JedisPool.class);
//        bind(JedisCluster.class).toProvider(JedisClusterProvider.class).asEagerSingleton();
//        expose(JedisCluster.class);
    }
}
