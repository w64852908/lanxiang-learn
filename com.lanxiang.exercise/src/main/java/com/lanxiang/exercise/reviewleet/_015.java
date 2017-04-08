package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lanjing on 2017/4/5.
 */
public class _015 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            find(result, nums, i + 1, nums.length - 1, nums[i]);
        }
        return result;
    }

    private void find(List<List<Integer>> result, int[] nums, int left, int right, int target) {
        while (left < right) {
            if (nums[left] + nums[right] + target == 0) {
                List<Integer> temp = new ArrayList<>();
                temp.add(target);
                temp.add(nums[left]);
                temp.add(nums[right]);
                result.add(temp);
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
                left++;
                right--;
            } else if (nums[left] + nums[right] + target > 0) {
                right--;
            } else {
                left++;
            }
        }
    }

    @Test
    public void run() {
        int[] arr = {1, 2, 0, -1, -4, -1};

        System.out.println(threeSum(arr));
    }
}
