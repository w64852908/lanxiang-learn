package com.lanxiang.guava.cache.methodinterceptor.service;

import com.lanxiang.guava.cache.methodinterceptor.annotation.Cached;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2016/10/12.
 */

@Slf4j
public class CacheService {

    @Cached(expireTime = 4, timeUnit = TimeUnit.SECONDS)
    public String getTime() {
        return "Current time: " + new Date().getTime();
    }

    @Cached(expireTime = 6, timeUnit = TimeUnit.SECONDS)
    public int getNum() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return new Random().nextInt(10000);
    }
}