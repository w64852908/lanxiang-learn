package com.lanxiang.guice.bindingannotation.service.impl;

import com.lanxiang.guice.bindingannotation.service.PrintService;

/**
 * Created by lanxiang on 2016/10/11.
 */
public class Print3ServiceImpl implements PrintService {
    public void print() {
        System.out.println("print3 method binding @Third annotations.");
    }
}