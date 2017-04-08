package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2017/4/8.
 */
public class _078 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        for (int i = 0; i <= nums.length; i++) {
            result.addAll(combine(nums, i));
        }
        return result;
    }

    private List<List<Integer>> combine(int[] arr, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (arr == null || arr.length == 0 || k == 0) {
            return result;
        }
        dfs(arr, k, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void dfs(int[] arr, int k, int start, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            temp.add(arr[i]);
            dfs(arr, k, i + 1, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void run() {
        int[] arr = {1, 2, 3};
        System.out.println(subsets(arr));
    }
}
