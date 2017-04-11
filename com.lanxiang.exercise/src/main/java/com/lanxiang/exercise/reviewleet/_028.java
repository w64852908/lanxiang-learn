package com.lanxiang.exercise.reviewleet;

/**
 * Created by lanjing on 2017/4/7.
 */

import org.junit.Test;

/**
 * Implement strStr().
 * Returns the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 * <p>
 * 实现从一个字符串里查找另一个字串
 */
public class _028 {

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int strLen = needle.length();
        int index = 0;
        while (index + strLen <= haystack.length()) {
            if (haystack.substring(index, index + strLen).equals(needle)) {
                System.out.println(haystack.substring(index, index + strLen));
                return index;
            }
            index++;
        }
        return -1;
    }

    @Test
    public void run() {
        String source = "今天是星期四,明天是星期五,后天就是周末啦啦啦啦,开心开心哈哈哈哈哈哈哈";
        String target1 = "周末";
        String target2 = "开心";
        String target3 = "星期四";
        System.out.println(strStr(source, target1));
        System.out.println(strStr(source, target2));
        System.out.println(strStr(source, target3));
    }
}
