package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/8.
 */
public class _069 {

    public int getSqrt(int num) {
        long left = 0;
        long right = num / 2 + 1;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid * mid == num) {
                return (int) mid;
            } else if (mid * mid < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) right;
    }

    @Test
    public void run() {
        int a = 9;
        int b = 64;
        int c = 1000;
        System.out.println(getSqrt(a));
        System.out.println(getSqrt(b));
        System.out.println(getSqrt(c));
    }
}
