package com.lanxiang.redis.exercise;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lanxiang.redis.resource.guice.JedisModule;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

/**
 * Created by lanjing on 2017/4/13.
 */
public class LoginLimit {

    private Jedis jedis;

    private final static String LOGIN_KEY = "login:";

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new JedisModule());
        jedis = injector.getProvider(JedisPool.class).get().getResource();
    }

    public void login(String userId) {
        if (userId == null) {
            return;
        }
        String key = getKey(userId);
        long now = new Date().getTime();
        long length = jedis.llen(key);
        //如果队列的元素大于或等于5个,则取出第四个元素的登陆时间,判断该时间是否超时
        if (length >= 5 && (now - Long.valueOf(jedis.lindex(key, 4))) <= 3600000) {
            throw new IllegalStateException("一个小时只能登陆5次");
        } else {
            System.out.println("登陆成功");
            //登陆成功,记录当前用户最后登陆时间,放在队头
            jedis.lpush(key, now + "");
            //如果队列元素超过五个,尝试清理一个小时之前的登陆记录
            if (length >= 5) {
                int index = 5;
                while (index < length) {
                    if (now - Long.valueOf(jedis.lindex(key, index)) > 3600000) {
                        break;
                    }
                    index++;
                }
                jedis.ltrim(key, 0, index - 1);
            }
        }
    }

    private String getKey(String userId) {
        return LOGIN_KEY + userId;
    }

    @Test
    public void run() {
        login("123");
    }
}
