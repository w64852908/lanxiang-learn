package com.lanxiang.hystrix.allfallback;

import java.util.Arrays;
import java.util.List;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * Created by lanxiang on 2018/11/9.
 */
public class CircuitBreaker extends HystrixCommand<String> {

    private int executeTime;

    public CircuitBreaker(int executeTime) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(1500)
                                .withCircuitBreakerSleepWindowInMilliseconds(10000)
                                .withCircuitBreakerErrorThresholdPercentage(50)
                                .withCircuitBreakerRequestVolumeThreshold(2)
                        )
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                                .withCoreSize(10).withMaxQueueSize(10)
                        )
        );
        this.executeTime = executeTime;
    }

    @Override
    protected String run() {
        try {
            Thread.sleep(executeTime);
        } catch (InterruptedException e) {
        }
        return "ok timeMillSeconds is :" + executeTime;
    }

    @Override
    protected String getFallback() {
        return "fall back timeMillSeconds is :" + executeTime;
    }

    public static void main(String[] args) {
        List<Integer> times = Arrays.asList(100, 200, 300, 400, 500, 600,
                700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100);
        int count = 0;
        while (true) {
            String output = new CircuitBreaker(times.get(count % times.size())).execute();
            System.out.println(output);
            count++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
