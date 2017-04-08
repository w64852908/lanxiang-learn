package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/4.
 */
public class _007 {

    public int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) rev;
    }

    @Test
    public void run() {
        int i = 123456789;
        System.out.println(reverse(i));
    }
}
