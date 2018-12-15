package com.lanxiang.exercise.sort;

import org.junit.Test;

/**
 * Created by lanjing on 2017/3/29.
 */
public class QuickSort {

    public void sort(int[] nums, int low, int high) {
        if (null == nums || nums.length == 0 || low >= high || low < 0 || high > nums.length - 1) {
            return;
        }
        int mid = divide(nums, low, high);
        sort(nums, low, mid - 1);
        sort(nums, high, mid + 1);
    }

    private int divide(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= temp) {
                right--;
            }
            nums[right] = nums[left];
            while (left < right && nums[left] <= temp) {
                left++;
            }
            nums[left] = nums[right];
        }
        nums[left] = temp;
        return left;
    }

    @Test
    public void run() {
//        int[] a = {5, 7, 3, 4, 6, 2, 1}
        int[] a = {1, 2, 3, 4, 5, 6};
        sort(a, 0, a.length - 1);
        for (int n : a) {
            System.out.print(n + " ");
        }
    }
}
