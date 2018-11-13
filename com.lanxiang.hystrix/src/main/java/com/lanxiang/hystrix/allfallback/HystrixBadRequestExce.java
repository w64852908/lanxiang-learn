package com.lanxiang.hystrix.allfallback;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by lanxiang on 2018/11/9.
 */

/**
 * 任务抛出了非HystrixBadRequestException的异常；
 */
public class HystrixBadRequestExce extends HystrixCommand<String> {

    public HystrixBadRequestExce() {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(15000))
        );
    }

    @Override
    protected String run() throws Exception {
        throw new NullPointerException();
    }

    @Override
    protected String getFallback() {
        return "exception fall back";
    }

    @Test
    public void test() {
        System.out.println(new HystrixBadRequestExce().execute());
    }
}
