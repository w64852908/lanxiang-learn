package com.lanxiang.redis.redisInAction.webApp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2017/2/9.
 */
@Slf4j
public class WebApp {

    private Jedis jedis;

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new JedisModule());
        jedis = injector.getProvider(JedisPool.class).get().getResource();
    }

    //更新token和商品浏览记录
    private void updateToken(Jedis jedis, String token, String user, String item) {
        Long timeStamp = new Date().getTime();
        //维持令牌与已登录用户之间的映射
        jedis.hset("login:", token, user);
        //记录令牌最后一次出现的时间
        jedis.zadd("recent:", timeStamp, token);
        if (item != null) {
            //记录用户浏览过的商品
            jedis.zadd("viewed:" + token, timeStamp, item);
            //移除旧的记录,只保留用户最近浏览过的25个商品
            jedis.zremrangeByRank("viewed:" + token, 0, -26);
        }
    }

    //尝试获取并返回令牌对应的用户
    private String checkToken(Jedis jedis, String token) {
        return jedis.hget("login:", token);
    }

    //清除session数据
    private void cleanSessions(Jedis jedis) throws Exception{
        long limit = 10;
        while (true) {
            //找出目前已有token的数量
            long size = jedis.zcard("recent:");
            //如果token数量没有超过限制,则进入休眠
            if (size <= limit) {
                TimeUnit.SECONDS.sleep(1);
            } else {
                long endIndex = size - limit < 100 ? size - limit : 100;
                //获取需要移除的token的id
                Set<String> tokens = jedis.zrange("recent:", 0, endIndex - 1);
                //移除旧的token
                for (String token : tokens) {
                    jedis.del("viewed:" + token);
                    jedis.hdel("login:", token);
                    jedis.zrem("recent:", token);
                }
            }
        }
    }

    @Test
    public void run() throws InterruptedException {
//        updateToken(jedis, "lanxiang_token", "lanxiang", null);
//        System.out.println(checkToken(jedis, "lanxiang_token"));
        for (int i = 0; i < 10000; i++) {
            updateToken(jedis, "lanxiang_token" + i, "lanxiang" + i, null);
            log.info("Add token {}.", i);
        }
    }

    @Test
    public void run2() throws Exception{
        cleanSessions(jedis);
    }
}
