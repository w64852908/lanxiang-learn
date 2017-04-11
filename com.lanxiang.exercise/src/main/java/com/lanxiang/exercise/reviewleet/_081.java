package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/8.
 */
public class _081 {

    private int result = -1;

    //找到转折的数的下标
    public int findRotateIndex(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        if (left < right && result == -1) {
            findRotateIndex(nums, left, mid);
            findRotateIndex(nums, mid + 1, right);
            int temp = getIndex(nums, left, right);
            result = temp > result ? temp : result;
        }
        if (result != -1) {
            return result;
        }
        return -1;
    }

    private int getIndex(int[] nums, int left, int right) {
        for (int i = left; i < right; i++) {
            if (i != left && i != right - 1) {
                if (nums[i] < left || nums[i] > right) {
                    return i;
                }
            } else if (i == left) {
                if (nums[i] > nums[i + 1]) {
                    return i;
                }
            } else {
                if (nums[i - 1] > nums[i]) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Test
    public void run() {
        int[] nums = {1, 2, 3, 4, 6, 8, 200, 300, 400, 500, 455};
        System.out.println(findRotateIndex(nums, 0, nums.length - 1));
    }
}
