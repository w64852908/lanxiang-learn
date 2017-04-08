package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2017/4/8.
 */
public class _077 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 0 || k == 0) {
            return result;
        }
        List<Integer> temp = new ArrayList<>();
        dfs(n, k, 1, temp, result);
        return result;
    }

    private void dfs(int n, int k, int start, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            dfs(n, k, i + 1, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void run() {
        int n = 4, k = 2;
        System.out.println(combine(n, k));
    }
}
