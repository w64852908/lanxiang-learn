package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/8.
 */
public class _062 {

    public int uniquePaths(int m, int n) {
        if (m == 0) {
            return n - 1;
        } else if (n == 0) {
            return m - 1;
        }
        int total = m + n - 2;
        int up = total - m + 1;

        int a = recursiveMulit(total, up);
        int b = recursiveMulit(up, up);
        return a / b;
    }

    private int recursiveMulit(int num, int n) {
        int result = 1;
        while (n > 0) {
            result = result * num--;
            n--;
        }
        return result;
    }


    @Test
    public void runTest() {
        int m = 4;
        int n = 3;
        System.out.println(uniquePaths(m, n));
    }
}
