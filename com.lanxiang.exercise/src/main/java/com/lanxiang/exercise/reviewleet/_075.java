package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/8.
 */
public class _075 {


    public void sortColors(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] count = new int[3];
        for (int a : arr) {
            count[a]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            int c = count[i];
            while (c > 0) {
                arr[index] = i;
                c--;
                index++;
            }
        }
    }

    @Test
    public void runTest() {
        int[] arr = {2, 1, 2, 0, 1, 0, 0, 2, 1, 1};
        sortColors(arr);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
