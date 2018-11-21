package com.lanxiang.hystrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

/**
 * Created by lanxiang on 2018/11/13.
 */
public class DegradeCalTest {

    @Test
    public void testDegrade() {
        int[] userIdArr = new int[100000];
        for (int i = 0; i < userIdArr.length; i++) {
            userIdArr[i] = i;
        }
        float res = calculateRate(userIdArr);
        System.out.println(res);
    }

    private float calculateRate(int[] userIdArr) {
        int num = 0, total = userIdArr.length;
        for (int userId : userIdArr) {
            if (userId % 10 < 3) {
                num++;
            }
        }
        return new BigDecimal(num).divide(new BigDecimal(total), 2, RoundingMode.HALF_UP).floatValue();
    }
}
