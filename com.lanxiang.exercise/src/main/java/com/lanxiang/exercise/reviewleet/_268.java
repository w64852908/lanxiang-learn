package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2017/4/9.
 */
public class _268 {


    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    @Test
    public void run() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12};
        System.out.println(missingNumber(arr));
    }
}
