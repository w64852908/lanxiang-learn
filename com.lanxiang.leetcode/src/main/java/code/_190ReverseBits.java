package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/26.
 */

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * <p>
 * Example:
 * <p>
 * Input: 43261596
 * Output: 964176192
 * Explanation: 43261596 represented in binary as 00000010100101000001111010011100,
 * return 964176192 represented in binary as 00111001011110000010100101000000.
 * Follow up:
 * If this function is called many times, how would you optimize it?
 * <p>
 * 要求：把一个32位的非负数整数按照位反转
 * <p>
 * 思路：只需要把要翻转的数从右向左一位位的取出来，如果取出来的是1，我们将结果res左移一位并且加上1；
 * 如果取出来的是0，我们将结果res左移一位，然后将n右移一位即可
 */
public class _190ReverseBits {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            //res左移一位，然后再判断n的最低位是否为1，是的话那么结果res加上1，然后将n右移一位即可，代码如下：
            //与操作，n为1的时候，条件成立
            if ((n & 1) == 1) {
                ++result;
            }
            n >>= 1;
        }
        return result;
    }

    @Test
    public void run() {
        int input = 43261596;
        System.out.println(reverseBits(input));

        int res = 5;
        res >>= 1;
        System.out.println(res);
        System.out.println(7 & 1);
    }
}
