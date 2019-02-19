package code;

/**
 * Created by lanjing on 2019/2/10.
 */

import org.junit.Test;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of
 * which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * Example:
 * <p>
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 * <p>
 * 要求：给一个数组和数字，要找到最短的子数组和大于该数字的子数组长度 plus：如果找到了O(n)的解，那么可以尝试O(nlogn)的解法
 * <p>
 * 思路：
 * 1.O(n)的解法，左右指针，如果sum比s小，则移动右指针，如果右指针越界，则跳出循环。如果sum比s大，更新minLen和sum，移动左指针，需要注意的
 * 是一些特殊测试用例。例如数组sum比s还要小的情况
 *
 * 2.
 */
public class _209MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0, minLen = Integer.MAX_VALUE, sum = nums[left];
        while (left <= right) {
            if (s > sum) {
                if (right < nums.length - 1) {
                    right++;
                    sum += nums[right];
                } else {
                    break;
                }
            } else {
                minLen = Math.min(right - left, minLen);
                sum -= nums[left];
                left++;
            }
        }
        if (minLen > nums.length && sum < s) {
            return 0;
        }
        return minLen + 1;
    }

    @Test
    public void run() {
//        int s = 4;
//        int[] nums = {1,4,4};
//        int s = 7;
//        int[] nums = {2,3,1,2,4,3};
//        int s = 11;
//        int[] nums = {1, 2, 3, 4, 5};
//        int s = 6;
//        int[] nums = {10, 2, 3};
        int s = 3;
        int[] nums = {1, 1};
        System.out.println(minSubArrayLen(s, nums));
    }
}
