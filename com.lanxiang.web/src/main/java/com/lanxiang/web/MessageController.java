package com.lanxiang.web;

import com.google.inject.Guice;
import com.lanxiang.event.RemindEvent;
import com.lanxiang.guice.ServiceModule;
import com.lanxiang.model.Response;
import com.lanxiang.rabbitmq.async.annotation.AsyncMark;
import com.lanxiang.rabbitmq.async.guice.AsyncModule;
import com.lanxiang.rabbitmq.async.register.AsyncRegister;
import com.lanxiang.service.MessageService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by lanxiang on 2016/9/27.
 */

@Path("/message")
@Singleton
@Slf4j
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class MessageController {

    private MessageService messageService;

    private AsyncRegister asyncRegister;

    @PostConstruct
    public void init() {
        asyncRegister = Guice.createInjector(new AsyncModule()).getProvider(AsyncRegister.class).get();
        messageService = Guice.createInjector(new ServiceModule()).getInstance(MessageService.class);
        asyncRegister.register(this);
    }

    @GET
    @Path("/exposeAllRegister")
    public Response exposeAllRegister() {
        messageService.testMessageService();
        return new Response().setStatus(0);
    }

    @AsyncMark
    public void asyncMark1(RemindEvent remindEvent) {

    }

    @AsyncMark
    public void asyncMark2(RemindEvent remindEvent) {

    }
}
