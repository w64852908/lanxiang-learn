package com.lanxiang.core.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanxiang.jackson.BaseObjectMapperProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by lanxiang on 2016/11/23.
 */

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {

    @Inject
    private BaseObjectMapperProvider normalMapper;

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return normalMapper.get();
    }
}
