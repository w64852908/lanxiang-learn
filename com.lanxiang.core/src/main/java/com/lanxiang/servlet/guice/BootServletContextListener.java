package com.lanxiang.servlet.guice;

import ch.qos.logback.core.spi.LifeCycle;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.lanxiang.guice.DefaultRuntimeModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.lifecycle.LifecycleManager;
import com.squarespace.jersey2.guice.BootstrapModule;
import com.squarespace.jersey2.guice.BootstrapUtils;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.api.ServiceLocator;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 2016/11/10.
 */

@Slf4j
@WebListener
public class BootServletContextListener extends GuiceServletContextListener {

    private ServiceLocator locator;

    private Injector injector;

    private LifecycleManager manager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
        try {
            manager = injector.getInstance(LifecycleManager.class);
            manager.start();
        } catch (Throwable t) {
            log.warn("LifecycleManager failed to start", t);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            manager.close();
        } catch (Throwable t) {
            log.warn("LifecycleManager failed to close", t);
        }
        ILoggerFactory factory = LoggerFactory.getILoggerFactory();
        log.info("Logger factory: {}", factory);
        if (factory instanceof LifeCycle) {
            log.info("Stop logger : {}", factory);
            LifeCycle lc = (LifeCycle) factory;
            try {
                lc.stop();
            } catch (Throwable t) {
                log.warn("Stop logger and ignore exception");
            }
        }
        super.contextDestroyed(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        try {
            initInjector();
        } catch (Throwable t) {
            log.error("Failed to init guice injector.", t);
        }
        return injector;
    }

    private void initInjector() {
        if (injector == null) {
            List<Module> modules = new ArrayList<>();
            modules.add(new DefaultRuntimeModule());
            modules.add(new ServletRuntimeModule());

            this.locator = BootstrapUtils.newServiceLocator();
            modules.add(new BootstrapModule(locator));

            this.injector = LifecycleInjector.builder().withModules(modules).build().createInjector();

            BootstrapUtils.link(locator, injector);
            BootstrapUtils.install(locator);
        }
    }
}
