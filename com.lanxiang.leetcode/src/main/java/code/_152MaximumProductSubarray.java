package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/11.
 * <p>
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * 要求：给一个数组，找到相邻的子数组，要求输出的乘积最大
 * <p>
 * 思路：dp，因为有负数的存在，所以要用两个dp数组，记录最大和最小值，每到一个位置，再拿上一个位置的最大和最小值和当前位置求乘积，得到最大值
 */
public class _152MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int preMin = nums[0], preMax = nums[0];
        int currMin, currMax;
        for (int i = 1; i < nums.length; i++) {
            currMin = Math.min(Math.min(preMax * nums[i], preMin * nums[i]), nums[i]);
            currMax = Math.max(Math.max(preMax * nums[i], preMin * nums[i]), nums[i]);
            preMin = currMin;
            preMax = currMax;
            max = Math.max(currMax, max);
        }
        return max;
    }

    @Test
    public void run() {
        int[] arr = {2, 3, -2, 4, 2, 3, -2};
        System.out.println(maxProduct(arr));
    }
}
