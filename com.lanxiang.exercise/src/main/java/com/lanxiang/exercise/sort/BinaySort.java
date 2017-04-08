package com.lanxiang.exercise.sort;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/4.
 */
public class BinaySort {

    public void binaySort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        int currVal, left, right, mid;
        for (int i = 1; i < a.length; i++) {
            currVal = a[i];
            left = 0;
            right = i - 1;
            while (left <= right) {
                mid = (right + left) / 2;
                if (a[mid] > currVal) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            a[left] = currVal;
        }
    }

    @Test
    public void run() {
        int[] a = {3, 8, 4, 6, 2, 1, 7, 100, 76, 45, 25, 66, 78};
        binaySort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
