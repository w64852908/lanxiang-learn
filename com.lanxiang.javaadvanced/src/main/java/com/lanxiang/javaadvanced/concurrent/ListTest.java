package com.lanxiang.javaadvanced.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2017/3/27.
 */
public class ListTest {

    @Test
    public void run1() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            list.add(i + "");
        }
        for (String str : list) {
            if ("2".equals(str)) {
                list.remove(str);
            }
        }
    }
}
