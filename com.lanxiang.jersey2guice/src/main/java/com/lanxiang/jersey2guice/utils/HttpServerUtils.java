package com.lanxiang.jersey2guice.utils;

import com.google.inject.servlet.GuiceFilter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.io.Closeable;
import java.io.IOException;
import java.util.EnumSet;
import java.util.EventListener;

/**
 * Created by lanxiang on 2016/11/30.
 */

@Slf4j
public class HttpServerUtils {

    public static final int PORT = 9091;

    private HttpServerUtils() {
    }

    public static HttpServer newHttpServer(Class<?>... rsrc) throws IOException {
        return newHttpServer(null, rsrc);
    }

    public static HttpServer newHttpServer(EventListener listener, Class<?>... rsrc) throws IOException {

        ResourceConfig config = new ResourceConfig();
        //加载所有的rest资源
        for (Class<?> clazz : rsrc) {
            log.info("Resource: {}", clazz);
            config.register(clazz);
        }

        ServletContainer servletContainer = new ServletContainer(config);

        ServletHolder servletHolder = new ServletHolder(servletContainer);
        Server server = new Server(PORT);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        FilterHolder filterHolder = new FilterHolder(GuiceFilter.class);
        context.addFilter(filterHolder, "/*", EnumSet.allOf(DispatcherType.class));
        context.addServlet(servletHolder, "/*");

        if (listener != null) {
            context.addEventListener(listener);
        }

        server.setHandler(context);

        try {
            server.start();
        } catch (Exception e) {
            throw new IOException(e);
        }

        return new HttpServer(server);
    }

    public static class HttpServer implements Closeable {

        private final Server server;

        public HttpServer(Server server) {
            this.server = server;
        }

        @Override
        public void close() throws IOException {
            try {
                server.stop();
            } catch (Exception e) {
                throw new IOException(e);
            }
        }
    }
}
