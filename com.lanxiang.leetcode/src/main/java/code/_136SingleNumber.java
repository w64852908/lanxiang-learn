package code;

/**
 * Created by lanjing on 2018/10/17.
 */

import org.junit.Test;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Note:
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * <p>
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [4,1,2,1,2]
 * Output: 4
 * <p>
 * 要求：一个数组，每个数都出现两次，只有一个数出现一次，找到单数
 * <p>
 * 思路：异或运算符是用符号“^”表示的，其运算规律是：两个操作数的位中，相同则结果为0，不同则结果为1。
 * 例如：a 的值是15，转换成二进制为1111，而b 的值是2，转换成二进制为0010，根据异或的运算规律，可以得出其结果为1101 即13。
 * <p>
 * 因为A XOR A = 0，且XOR运算是可交换的，于是，对于实例{2,1,4,5,2,4,1}就会有这样的结果：
 * (2^1^4^5^2^4^1) => ((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5
 */
public class _136SingleNumber {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }

    @Test
    public void run() {
        int[] arr = {1, 2, 1};
        System.out.println(singleNumber(arr));
        System.out.println(1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7);
    }
}
