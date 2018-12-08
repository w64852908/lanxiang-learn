package com.lanxiang.javaadvanced.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lanjing on 2018/12/4.
 */
public class MapTest {

    @Test
    public void testMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        System.out.println(map);
        map.put("a", 2);
        System.out.println(map);
        System.out.println(map.containsValue(1));
    }

    @Test
    public void testConflict() {
        String s = "cabc";
        System.out.println(s.substring(0, 1));
    }
}
