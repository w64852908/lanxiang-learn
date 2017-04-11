package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

import java.util.*;

/**
 * Created by lanjing on 2017/4/8.
 */


/**
 * 分组
 */
public class _049 {

    public List<List<String>> getGroup(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] strSeq = str.toCharArray();
            Arrays.sort(strSeq);
            String sortedStr = new String(strSeq);
            if (map.containsKey(sortedStr)) {
                List<String> value = map.get(sortedStr);
                value.add(str);
            } else {
                List<String> value = new ArrayList<>();
                value.add(str);
                map.put(sortedStr, value);
            }
        }
        for (String sortedStr : map.keySet()) {
            result.add(map.get(sortedStr));
        }
        return result;
    }

    @Test
    public void run() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat", "abt", "tab"};
        System.out.println(getGroup(strs));
    }
}
