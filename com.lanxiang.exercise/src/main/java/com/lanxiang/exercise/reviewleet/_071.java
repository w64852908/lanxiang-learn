package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lanjing on 2017/4/8.
 */
public class _071 {


    public String simplifyPath(String path) {
        if (path == null) {
            return null;
        }
        String[] paths = path.split("/");
        Map<Integer, String> map = new HashMap<>();
        int i = 0;
        for (String str : paths) {
            if (!str.equals("") && !str.equals(".")) {
                if (str.equals("..")) {
                    if (map.keySet().size() > 0) {
                        map.remove(--i);
                    }
                } else {
                    map.put(i++, str);
                }
            }
        }
        StringBuilder result = new StringBuilder("/");
        int count = 0;
        for (int n : map.keySet()) {
            count++;
            result.append(map.get(n));
            if (count != map.keySet().size()) {
                result.append("/");
            }
        }
        return result.toString();
    }

    @Test
    public void run() {
        String path = "/a/./b/../../c/";
        String path1 = "/Users/lanjing";
        System.out.println(simplifyPath(path));
        System.out.println(simplifyPath(path1));
    }
}
