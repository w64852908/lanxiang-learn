package code;

/**
 * Created by lanjing on 2018/12/12.
 */

import org.junit.Test;
import properties.ArrayOutputUtil;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product
 * of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the
 * purpose of space complexity analysis.)
 * <p>
 * <p>
 * 要求：输入一个数组，输出一个数组，结果数组的每一位为其他位的所有乘积（要求线性时间）
 * <p>
 * 思路：先从前面遍历一遍，将乘积的累积存入res中，然后从后面开始遍历，用到一个临时变量right，初始化为1，然后每次不断累积，最终得到正确结果
 */
public class _238ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        if (null == nums || nums.length == 0) {
            return null;
        }
        int n = nums.length, right = 1;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];
        }
        return res;
    }


    @Test
    public void run() {
        int[] arr = {1, 2, 3, 4};
        ArrayOutputUtil.printArray(productExceptSelf(arr));
    }

}
