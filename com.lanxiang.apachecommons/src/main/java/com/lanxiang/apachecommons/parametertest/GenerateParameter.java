package com.lanxiang.apachecommons.parametertest;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.lanxiang.apachecommons.utils.MD5Util;

import jersey.repackaged.com.google.common.collect.Maps;

/**
 * Created by lanxiang on 2017/5/9.
 */
public class GenerateParameter {

    private static final String KEY_1000007 = "4e60d0d79892bac0b34964eee4f34c48";

    private static final String KEY_1000014 = "A013F70DB97834C0A5492378BD76C53A";

    private static final String KEY_1000029 = "ebevdfqkdohjhtgue6xuk443qyxldhlz";

    private static final String KEY_1000021 = "eo82ptgv2607ehu7h8qeq9ri5flmp78t";

    private static final String KEY_1000006 = "1001bf03c56552f2814cff37d6af7604";

    private String key;

    @Before
    public void init() {
        key = KEY_1000007;
    }

    @Test
    public void generate() {
        TreeMap<String, Object> params = Maps.newTreeMap();
        params.put("api", "gateway.sync.halls");
        params.put("bizData", "{\"cinemaId\":1564}");
        params.put("merCode", "1000007");
        params.put("signType", "MD5");
        params.put("timestamp", "2690258952");
        params.put("version", "1.0");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (null != entry.getValue()) {
                if (StringUtils.isNotBlank(entry.getValue().toString()))
                    sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        String s = sb.toString() + "key=" + key;
        System.out.println(s);
        String sign = MD5Util.md5(s).toUpperCase();
        System.out.println(sign);
    }
}
