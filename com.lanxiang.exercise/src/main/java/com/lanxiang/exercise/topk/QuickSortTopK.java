package com.lanxiang.exercise.topk;

import com.lanxiang.exercise.util.ArrayOutputUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2018/11/24.
 */
public class QuickSortTopK {

    public int[] getTopK(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k > nums.length) {
            return null;
        }
        //获取第一次排序完的中轴
        int mid = divide(nums, 0, nums.length - 1);
        //中轴和k的位置不一致
        while (mid != k) {
            //如果中轴在k的左边，说明需要往右边排序，反之
            if (mid < k) {
                mid = divide(nums, mid + 1, nums.length - 1);
            } else {
                mid = divide(nums, 0, mid - 1);
            }
        }
        return Arrays.copyOf(nums, k);
    }

    private int divide(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= temp) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= temp) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = temp;
        return left;
    }

    @Test
    public void run() {
        int[] a = {5, 2, 8, 9, 2, 3, 4, 9};
        int[] res = getTopK(a, 3);
        for (int n : res) {
            System.out.print(n + " ");
        }
    }
}
