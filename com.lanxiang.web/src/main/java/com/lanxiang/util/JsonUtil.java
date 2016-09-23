package com.lanxiang.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lanxiang on 16/9/2.
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static String convertObjectToString(Object object) {
        String result = "";
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public static Object convertStringToObject(String json, Class<?> clazz) {
        Object object = null;
        try {
            object = mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return object;
    }
}
