package com.lanxiang.guice.bindingannotation.run;

import com.google.inject.Guice;
import com.lanxiang.guice.bindingannotation.guice.BindingAnnotationModule;
import com.lanxiang.guice.bindingannotation.service.impl.RunServiceImpl;
import org.junit.Test;

/**
 * Created by lanxiang on 2016/10/11.
 */
public class Run {

    @Test
    public void run(){
        RunServiceImpl runService = Guice.createInjector(new BindingAnnotationModule()).getInstance(RunServiceImpl.class);
        runService.print3();
        runService.print4();
    }
}