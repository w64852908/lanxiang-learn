package code;

/**
 * Created by lanjing on 2018/12/8.
 */

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p/>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p/>
 * How many possible unique paths are there?
 * <p/>
 * <p/>
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * <p/>
 * Note: m and n will be at most 100.
 * <p/>
 * 要求：一个机器人在长方形的左上角，每次只能向右或者向下走，它要到达右下角，一共有多少种走法？
 * <p/>
 * 思路：动态规划，到第一列和第一行每一个格子的走法都是1，而且a[1][1] = a[0][1] + a[1][0];
 */
public class _062UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
