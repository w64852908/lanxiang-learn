package com.lanxiang.morphia.model;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lanxiang on 16/9/21.
 */
public class Concurrent extends MongoPersistenceObject {

    private String uniqueId;

    private Long insertNumber;

    private Map<String, List<String>> educationMap;

    private String insertDate;

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private final static Random random = new Random();

    public Concurrent() {
    }

    public Concurrent(Long insertNumber) {
        this.uniqueId = UUID.randomUUID().toString();
        this.insertNumber = insertNumber;
        this.educationMap = generateMap();
        this.insertDate = format.format(new Date());
    }

    private Map<String, List<String>> generateMap() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        String key1 = "分水小学";
        String key2 = "分水中学";
        String key3 = "万州中学";
        String key4 = "大连理工大学";
        List<String> list1 = randomList(6);
        List<String> list2 = randomList(3);
        List<String> list3 = randomList(4);
        List<String> list4 = randomList(4);
        map.put(key1, list1);
        map.put(key2, list2);
        map.put(key3, list3);
        map.put(key4, list4);
        return map;
    }

    private List<String> randomList(int length) {
        List<String> result = new ArrayList<String>(length);
        for (int i = 0; i < length; i++) {
            result.add(String.valueOf(2000 + random.nextInt(16)));
        }
        return result;
    }
}
