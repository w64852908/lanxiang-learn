package com.lanxiang.morphia.objectid;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by lanxiang on 2017/4/14.
 */
public class ObjectIdConcern {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init(){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void run() throws IOException {
        String source = "58f0af2df11b5c12a564996e";
        ObjectId objectId = new ObjectId(source);
        String jsonData = objectMapper.writeValueAsString(objectId);
        System.out.println(jsonData);
        System.out.println(objectMapper.readValue(jsonData, ObjectId.class));
    }
}
