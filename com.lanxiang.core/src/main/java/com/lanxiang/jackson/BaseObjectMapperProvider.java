package com.lanxiang.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

/**
 * Created by lanxiang on 2016/11/23.
 */

@Slf4j
@Singleton
public class BaseObjectMapperProvider implements Provider<ObjectMapper> {

    private ObjectMapper objectMapper;

    @Override
    public ObjectMapper get() {
        if(objectMapper == null){
            init();
        }
        return objectMapper;
    }

    protected void init() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
        log.info("Init objectMapper");
    }
}
