package code;

/**
 * Created by lanjing on 2018/10/22.
 */

import org.junit.Test;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 * because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * 要求：抢劫，所有房子围成一个环，不能抢劫相连的两个房子，求抢劫的最大收益
 * <p>
 * 思路：动态规划，用dp[nums.length]数组，记录当前位置的最大收益
 * 需要找到dp[k] = Math.max(dp[k-1], dp[k-2] + nums[k]) 的规律
 */
public class _213HouseRobber2 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        //环，所以拆分成从0开始，去掉tail 或者 从1开始
        int max1 = innerRob(nums, 0, nums.length - 2);
        int max2 = innerRob(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }


    public int innerRob(int[] nums, int i, int j) {
        //如果数组只有一个元素
        if (i == j) {
            return nums[i];
        }
        int[] dp = new int[nums.length];
        //起始位置的最大收益，就是本身
        dp[i] = nums[i];
        //第二个位置的最大收益，是当前位置或者前一个位置的最大收益
        dp[i + 1] = Math.max(nums[i + 1], dp[i]);
        //从第三个位置开始遍历，dp = 前一个位置大于当前位置和前两个位置的和的话 取前一个位置，否则取当前位置+前两个位置的最大收益
        for (int k = i + 2; k <= j; k++) {
            dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k]);
        }
        //最后一位就是最大收益
        return dp[j];
    }

    @Test
    public void run() {
//        int[] arr = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
//        int[] arr = {2, 3, 2};
//        int[] arr = {8, 2, 8, 9, 2};
        int[] arr = {2, 7, 7, 7, 4};
        System.out.println(rob(arr));
    }

    private void show(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }
}
