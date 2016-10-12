package com.lanxiang.guava.cache.methodinterceptor.run;

import com.google.inject.Guice;
import com.lanxiang.guava.cache.methodinterceptor.guice.CacheModule;
import com.lanxiang.guava.cache.methodinterceptor.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2016/10/12.
 */
@Slf4j
public class Run {

    CacheService cacheService;

    @Before
    public void init() {
        cacheService = Guice.createInjector(new CacheModule())
                .getInstance(CacheService.class);
    }

    @Test
    public void run() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            log.info(cacheService.getTime());
            log.info("num : " + cacheService.getNum());
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        }
    }
}