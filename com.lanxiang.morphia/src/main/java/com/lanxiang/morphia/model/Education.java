package com.lanxiang.morphia.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lanxiang on 16/9/6.
 */
public class Education {

    Map<String, String> map;

    public Education() {
        this.map = new HashMap<String, String>();
    }

    public Education(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
