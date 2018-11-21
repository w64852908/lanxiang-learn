package code;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by lanjing on 2018/11/18.
 */

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Example 1:
 * <p>
 * Input: 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 * <p>
 * Input: 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Note: Your solution should be in logarithmic time complexity.
 * <p>
 * 要求：给一个数，找到这个数阶乘后有几个零
 * <p>
 * 思路：
 * 1.需要注意的是后缀0是由2，5相乘得来，因此只需看有多少个2，5即可
 * 2.质因子中2的个数总是大于等于5的个数。因此只要计数5的个数就可以了。
 * 3.需要注意的是25中有25，20，15，10，5，但是25又可以分为5*5
 */
public class _172FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            res = res + n / 5;
            n = n / 5;
        }
        return res;
    }

    @Test
    public void run() {
        System.out.println(trailingZeroes(30));
    }
}
