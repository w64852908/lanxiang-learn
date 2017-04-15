package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2017/4/15.
 */
public class _039 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        List<Integer> temp = new ArrayList<>();
        for (int k = 1; k <= candidates.length; k++) {
            recursive(candidates, 0, k, target, temp, result);
        }
        return result;
    }

    private void recursive(int[] arr, int start, int k, int target, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == k) {
            if (calcu(temp) == target) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start; i < arr.length; i++) {
            if (target < arr[i]) {
                return;
            }
            temp.add(arr[i]);
            recursive(arr, i, k, target, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    private int calcu(List<Integer> list) {
        int result = 0;
        for (Integer i : list) {
            result += i;
        }
        return result;
    }

    @Test
    public void run() {
//        int[] arr = {2, 3, 6, 7};
//        int target = 7;
        int[] arr = {1};
        int target = 2;
        System.out.println(combinationSum(arr, target));
    }

}
