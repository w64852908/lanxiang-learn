package com.lanxiang.exercise.handwrite;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2017/4/17.
 */

//字符串的全排列
public class TotalSitutation {

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void recursive(char[] arr, int start, int limit, List<String> result) {
        if (limit < 1) {
            return;
        }
        if (start == limit) {
            result.add(new String(arr));
        }
        for (int i = start; i < limit; i++) {
            swap(arr, i, start);
            recursive(arr, start + 1, limit, result);
            swap(arr, start, i);
        }
    }

    public List<String> getTotal(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        char[] arr = s.toCharArray();
        recursive(arr, 0, arr.length, result);
        return result;
    }

    @Test
    public void run() {
        String s = "abc";
        System.out.println(getTotal(s));
    }
}
