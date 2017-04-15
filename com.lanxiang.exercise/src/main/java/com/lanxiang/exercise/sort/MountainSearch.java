package com.lanxiang.exercise.sort;

/**
 * Created by lanjing on 2017/4/15.
 */

import org.junit.Test;

/**
 * 搜索数组的最大值,两边的值
 */
public class MountainSearch {

    public int search(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (arr[mid] > left && arr[mid] > right) {
                return mid;
            } else if (arr[mid] > arr[left] && arr[mid] < arr[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    @Test
    public void run() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 100, 77, 55, 32, 10, 6, 5, 2, 1};
        System.out.println(search(arr));
    }
}
