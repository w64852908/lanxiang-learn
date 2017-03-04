package com.lanxiang.jersey;

import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

import javax.ws.rs.ext.Provider;

/**
 * Created by lanxiang on 2016/11/18.
 */

@Slf4j
@Provider
public class JerseyEventListener implements ApplicationEventListener {

    @Override
    public void onEvent(ApplicationEvent event) {
        switch (event.getType()) {
            case INITIALIZATION_FINISHED:
                log.info("Application {} was initalized.", event.getResourceConfig().getApplicationName());
                break;
            case DESTROY_FINISHED:
                log.info("Application {} was destroyed.", event.getResourceConfig().getApplicationName());
                break;
        }
    }

    @Override
    public RequestEventListener onRequest(RequestEvent requestEvent) {
        return null;
    }
}
