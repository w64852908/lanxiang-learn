package com.lanxiang.guice.provides;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lanxiang on 2016/11/21.
 */

@Slf4j
public class Run {

    private Injector injector;

    @Before
    public void init() {
        injector = Guice.createInjector(new AModule());
    }

    @Test
    public void testProvides() {
        log.info(injector.getInstance(WebApplication.class).toString());
        if (injector.getInstance(WebApplication.class).equals(injector.getInstance(WebApplication.class))) {
            log.info("Equals");
        }
    }
}
