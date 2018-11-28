package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/27.
 */

/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 * <p>
 * Example 1:
 * <p>
 * Input: 11
 * Output: 3
 * Explanation: Integer 11 has binary representation 00000000000000000000000000001011
 * Example 2:
 * <p>
 * Input: 128
 * Output: 1
 * Explanation: Integer 128 has binary representation 00000000000000000000000010000000
 * <p>
 * 要求：输入一个十进制的数，要求计算这个数的二进制位为1的个数
 * <p>
 * 思路：从右开始每一位和1相与，如果为1，res++，然后对n进行右移1位操作
 */
public class _191NumberOfBits {

    public int hammingWeight(int n) {
        int res = 0;
        //这里不能用while(n>0)来当循环终止条件，在Integer.MAX_VALUE时会导致溢出
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                res++;
            }
            n >>= 1;
        }
        return res;
    }

    @Test
    public void run() {
        System.out.println(hammingWeight(7));
    }
}
