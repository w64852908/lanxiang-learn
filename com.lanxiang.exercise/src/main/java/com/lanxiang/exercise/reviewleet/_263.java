package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/9.
 */
public class _263 {

    public boolean isUgly(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        while (n % 2 == 0) {
            n = n / 2;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        while (n % 5 == 0) {
            n = n / 5;
        }
        return n == 1;
    }

    @Test
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (isUgly(i)) {
                System.out.println(i);
            }
        }
    }
}
