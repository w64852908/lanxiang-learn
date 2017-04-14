package com.lanxiang.exercise.basic;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/12.
 */
public class Breakok {

    /**
     * java跳出多层循环
     */

    @Test
    public void run() {
        int count = 0;
        ok:for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                count++;
                if (count == 50) {
                    break ok;
                }
            }
        }
        System.out.println(count);
    }
}
