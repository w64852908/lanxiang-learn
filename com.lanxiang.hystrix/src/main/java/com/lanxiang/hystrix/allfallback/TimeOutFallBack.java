package com.lanxiang.hystrix.allfallback;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by lanxiang on 2018/11/9.
 */

/**
 * 任务超过了"withExecutionTimeoutInMilliseconds"定义的超时时间；
 */
public class TimeOutFallBack extends HystrixCommand<String> {

    public TimeOutFallBack() {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000))
        );
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(2000);
        return "hello";
    }

    @Override
    protected String getFallback() {
        return "time out and get fall back";
    }

    @Test
    public void test() {
        System.out.println(new TimeOutFallBack().execute());
    }
}
