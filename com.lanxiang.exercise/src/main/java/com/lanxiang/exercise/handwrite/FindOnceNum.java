package com.lanxiang.exercise.handwrite;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/16.
 */
public class FindOnceNum {

    public int findOnce(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int[] appear = new int[arr.length];
        for (int a : arr) {
            appear[a]++;
        }
        for (int i = 0; i < appear.length; i++) {
            if (appear[i] == 1) {
                return i;
            }
        }
        return -1;
    }


    @Test
    public void run() {
        int[] arr = {1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6, 7, 7};
        System.out.println(findOnce(arr));
    }
}
