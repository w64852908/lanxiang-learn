package com.lanxiang.guice.bindingannotation.service.impl;

import com.google.inject.Inject;
import com.lanxiang.guice.bindingannotation.annotation.Fourth;
import com.lanxiang.guice.bindingannotation.annotation.Third;
import com.lanxiang.guice.bindingannotation.service.PrintService;

/**
 * Created by lanxiang on 2016/10/11.
 */
public class RunServiceImpl {

    @Inject
    @Third
    PrintService printService3;

    @Inject
    @Fourth
    PrintService printService4;

    public void print3(){
        printService3.print();
    }

    public void print4(){
        printService4.print();
    }
}