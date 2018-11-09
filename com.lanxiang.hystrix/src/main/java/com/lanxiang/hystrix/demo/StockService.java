package com.lanxiang.hystrix.demo;

import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2018/11/8.
 */
public class StockService {

    public String getStock() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "red stock";
    }

}
