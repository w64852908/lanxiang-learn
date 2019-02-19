package code;

/**
 * Created by lanjing on 2019/2/6.
 */

import org.junit.Test;
import properties.ArrayOutputUtil;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * <p>
 * Example 1:
 * <p>
 * Input: [5,7]
 * Output: 4
 * Example 2:
 * <p>
 * Input: [0,1]
 * Output: 0
 * <p>
 * 要求：给定一个范围，输出这个范围内所有数按位与的结果
 * <p>
 * 思路：，直接平移m和n，每次向右移一位，直到m和n相等，记录下所有平移的次数i，然后再把m左移i位即为最终结果
 */
public class _201BitwiseANDofNumbersRange {

    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            ++i;
        }
        return (m << i);
    }

    @Test
    public void run() {
        int m = 2, n = 2;
        int res = rangeBitwiseAnd(m, n);
        System.out.println(res);
    }
}
