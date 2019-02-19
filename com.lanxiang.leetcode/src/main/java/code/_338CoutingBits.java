package code;

/**
 * Created by lanxiang on 2018/12/13.
 */

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 * <p>
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 * <p>
 * 要求：输入一个数字，输出一个数组，返回这个数字从0-自己时，每一个数字二进制时1的个数
 * <p>
 * 思路：画图可知，如果i % 2 == 0，当前数和右移一位的1的个数是一致的。
 * 如果i % 2 == 1，当前数和右移一位的1的个数+1是一致的
 */
public class _338CoutingBits {

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1) {
                result[i] = result[i >> 1] + 1;
            } else {
                result[i] = result[i >> 1];
            }
        }
        return result;
    }

}