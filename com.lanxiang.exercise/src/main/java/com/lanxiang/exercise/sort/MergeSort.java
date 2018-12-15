package com.lanxiang.exercise.sort;

import org.junit.Test;

/**
 * Created by lanjing on 2017/3/28.
 */
public class MergeSort {

    public int[] sort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            //左边
            sort(nums, low, mid);
            //右边
            sort(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }
        return nums;
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        for (int n = 0; n < temp.length; n++) {
            nums[n + left] = temp[n];
        }
    }

    @Test
    public void run() {
        int[] a = {2, 4, 9, 3, 6, 7, 1, 5};
        int[] result = sort(a, 0, a.length - 1);
        for (int n : result) {
            System.out.print(n + " ");
        }
    }
}
