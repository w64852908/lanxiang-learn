package com.lanxiang.guice.bindingannotation.guice;

import com.google.inject.PrivateModule;
import com.lanxiang.guice.bindingannotation.annotation.Fourth;
import com.lanxiang.guice.bindingannotation.annotation.Third;
import com.lanxiang.guice.bindingannotation.service.PrintService;
import com.lanxiang.guice.bindingannotation.service.impl.Print3ServiceImpl;
import com.lanxiang.guice.bindingannotation.service.impl.Print4ServiceImpl;
import com.lanxiang.guice.bindingannotation.service.impl.RunServiceImpl;

/**
 * Created by lanxiang on 2016/10/11.
 */
public class BindingAnnotationModule extends PrivateModule{
    @Override
    protected void configure() {
        bind(PrintService.class).annotatedWith(Third.class).to(Print3ServiceImpl.class).asEagerSingleton();
        bind(PrintService.class).annotatedWith(Fourth.class).to(Print4ServiceImpl.class).asEagerSingleton();

        bind(RunServiceImpl.class);
        expose(RunServiceImpl.class);
    }
}