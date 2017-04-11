package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/8.
 */

/**
 * 0, 1, 1, 2, 3, 5, 8
 */
public class _070 {

    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        int curr = 0;
        int next = 1;
        for (int i = 1; i < n; i++) {
            int temp = curr + next;
            curr = next;
            next = temp;
        }
        return curr;
    }

    @Test
    public void run() {
        int n = 7;
        System.out.println(climbStairs(n));
    }
}
