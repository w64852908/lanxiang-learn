package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/25.
 * <p>
 * <p>
 * https://www.programcreek.com/wp-content/uploads/2016/04/longest-increasing-subsequence-leetcode-java-730x888.jpg
 * <p>
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * Example:
 * <p>
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * <p>
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 * <p>
 * <p>
 * 要求：输入一个数字，输出数组的最大递增序列的长度，可以不连续
 * <p>
 * 思路：动态规划的思想，在数组的每个下标都计算一下当前位置的最大递增序列的长度。
 */
public class _300LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int[] max = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            max[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max[i] = Math.max(max[i], max[j] + 1);
                }
            }
        }
        int result = 1;
        for (int m : max) {
            if (m > result) {
                result = m;
            }
        }
        return result;
    }

    @Test
    public void run() {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(arr));
    }
}
