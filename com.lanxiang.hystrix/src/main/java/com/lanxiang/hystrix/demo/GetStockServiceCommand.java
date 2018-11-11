package com.lanxiang.hystrix.demo;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * Created by lanxiang on 2018/11/8.
 */
public class GetStockServiceCommand extends HystrixCommand<String> {

    private StockService stockService;

    public GetStockServiceCommand() {
        super(setter());
    }

    private static Setter setter() {
        //服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("stock");
        //服务标识
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("getStock");
        //线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("stock-pool");

        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter()
                .withCoreSize(5)
                .withKeepAliveTimeMinutes(5)
                .withMaxQueueSize(Integer.MAX_VALUE)
                .withQueueSizeRejectionThreshold(50);

        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);

        return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey)
                .andThreadPoolKey(threadPoolKey).andThreadPoolPropertiesDefaults(threadPoolProperties)
                .andCommandPropertiesDefaults(commandProperties);
    }

    private void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    protected String run() throws Exception {
        String s = getThreadInfo() + "execute success : " + stockService.getStock();
        System.out.println(s);
        return s;
    }

    @Override
    protected String getFallback() {
        return getThreadInfo() + "I have no hystrix thread pool resource to execute.";
    }

    private String getThreadInfo() {
        return "[" + Thread.currentThread().getName() + "] ------ ";
    }

    @Test
    public void testHystrix() {
        final StockService stockService = new StockService();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GetStockServiceCommand command = new GetStockServiceCommand();
                    command.setStockService(stockService);
                    command.execute();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {

        }
    }
}
