package com.lanxiang.exercise.sort;

import org.junit.Test;

/**
 * Created by lanjing on 2017/3/27.
 */
public class BinarySearch {

    public int binaySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void run() {
        int[] arr = {1, 4, 7, 8, 10, 12, 55, 79, 100, 233, 456, 777, 999, 1234, 2456, 7788, 9999};
        int target = 777;
        System.out.println(binaySearch(arr, target));
    }
}
