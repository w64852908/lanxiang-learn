package com.lanxiang.jersey2guice.aop;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.lanxiang.jersey2guice.utils.HttpServerUtils;
import com.squarespace.jersey2.guice.BootstrapUtils;
import org.glassfish.hk2.api.ServiceLocator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lanxiang.jersey2guice.utils.HttpServerUtils.HttpServer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by lanxiang on 2016/11/30.
 */
public class AopTest {

    @Test
    public void checkAOP() throws IOException {
        final MyInterceptor interceptor = new MyInterceptor();

        AbstractModule aopModule = new AbstractModule() {
            @Override
            protected void configure() {
                bind(MyResource.class);
                bindInterceptor(Matchers.any(), Matchers.annotatedWith(MyAnnotation.class), interceptor);
            }
        };

        //服务器定位模式,加载所有的module资源
        ServiceLocator locator = BootstrapUtils.newServiceLocator();

        List<Module> modules = new ArrayList<>();
        modules.add(aopModule);

        @SuppressWarnings("unused")
        Injector injector = BootstrapUtils.newInjector(locator, modules);

        BootstrapUtils.install(locator);

        HttpServer server = HttpServerUtils.newHttpServer(MyResource.class);
        check();

        assertEquals(interceptor.counter.get(), 1);
    }

    //模拟请求
    private void check() throws IOException {
        assertTrue("jersey2guice is not installed.", BootstrapUtils.isInstalled());

        String url = "http://localhost:" + HttpServerUtils.PORT;

        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target(url).path(MyResource.PATH);

            String value = target.request(MediaType.TEXT_PLAIN).get(String.class);
            assertEquals(value, MyResource.RESPONSE);
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            client.close();
        }
    }
}
