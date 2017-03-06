package com.lanxiang.jersey.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanxiang.jersey.client.remote.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanxiang on 2017/3/6.
 */
@Slf4j
public class RestApi {

    private final static String REST_URL = "http://localhost:9090/";

    private static Map<Class<?>, Object> restInstance = new HashMap<>();

    private final static Integer CONNECTION_TOTAL = 50;

    private final static Integer DEFAULT_MAX_PER_ROUTE = 50;

    private final static Integer VALIDATE_AFTER_INACTIVITY = 60000;

    static {
        ClientConfig clientConfig = new ClientConfig();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(CONNECTION_TOTAL);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        connectionManager.setValidateAfterInactivity(VALIDATE_AFTER_INACTIVITY);

        clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
        WebTarget target = ClientBuilder.newClient(clientConfig)
                .register(JacksonFeature.class).register(ObjectMapper.class).target(REST_URL);

        restInstance.put(InfoService.class, bindService(InfoService.class, target));
    }

    private static <T> T bindService(Class<T> clazz, WebTarget target) {
        List<Cookie> cookieList = new ArrayList<>();
        return WebResourceFactory.newResource(clazz, target, false,
                new MultivaluedHashMap<String, Object>(), cookieList, new Form());
    }

    public static <T> T getService(Class<T> tClass) {
        return (T) restInstance.get(tClass);
    }
}
