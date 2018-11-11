package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/11.
 */

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 * <p>
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * Note: You may assume that n is not less than 2 and not larger than 58.
 * <p>
 * 要求：给一个数字，要求输出所有组成这个数字和的最大乘积
 * <p>
 * 思路：写出0-9的所有情况，找规律，发现需要拆成3最多的情况，如果除3余1，则最后一次改为乘4，如果除3余2，则为3的n次方再*2；
 */
public class _343IntegerBreak {

    public int integerBreak(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 3) {
            return n - 1;
        }
        int threeCount = 0;
        while (n >= 3) {
            n = n - 3;
            threeCount++;
        }
        int res = 1;
        if (n == 1) {
            res = (int) Math.pow(3, threeCount - 1);
            res = res * 4;
        } else {
            res = (int) Math.pow(3, threeCount);
            if (n == 2) {
                res = res * 2;
            }
        }
        return res;
    }

    @Test
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "-" + integerBreak(i));
        }
    }
}
