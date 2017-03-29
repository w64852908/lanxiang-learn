package com.lanxiang.exercise.sort;

import org.junit.Test;

/**
 * Created by lanjing on 2017/3/29.
 */
public class QuickSort {

    public void sort(int[] nums, int low, int high) {
        if (low < high) {
            int mid = divide(nums, low, high);
            sort(nums, low, mid - 1);
            sort(nums, mid + 1, high);
        }
    }

    private int divide(int[] nums, int low, int high) {
        //数组的第一个数作为中轴
        int temp = nums[low];
        while (low < high) {
            //从右到左,找到第一个比中轴小的数
            while (low < high && nums[high] >= temp) {
                high--;
            }
            //比中轴小的数移到左边
            nums[low] = nums[high];
            //从左到右,找到第一个比中轴大的数
            while (low < high && nums[low] <= temp) {
                low++;
            }
            //比中轴大的数移到右边
            nums[high] = nums[low];
        }
        //位置移动完一轮后,记录中轴最后的值
        nums[low] = temp;
        return low;
    }

    @Test
    public void run() {
        int[] a = {2, 4, 9, 3, 6, 7, 1, 5};
        sort(a, 0, a.length - 1);
        for (int n : a) {
            System.out.print(n + " ");
        }
    }
}
