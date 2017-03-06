package com.lanxiang.jersey.client.model;

import lombok.Data;

import java.util.Map;

@Data
public class SystemInfo {

    private Map<String, String> environmentMap;

    private Map<String, String> propertyMap;
}
