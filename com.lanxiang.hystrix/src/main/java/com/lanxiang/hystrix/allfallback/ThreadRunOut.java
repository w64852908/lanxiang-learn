package com.lanxiang.hystrix.allfallback;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * Created by lanxiang on 2018/11/9.
 */

/**
 * 超过了线程池线程数目；
 */
public class ThreadRunOut extends HystrixCommand<String> {

    public ThreadRunOut() {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(15000))
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(3))
        );
    }

    @Override
    protected String run() throws Exception {
        return "command execute success";
    }

    @Override
    protected String getFallback() {
        return "command fall back cause no thread available";
    }

    @Test
    public void test() {
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new ThreadRunOut().execute());
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
        }
    }
}
