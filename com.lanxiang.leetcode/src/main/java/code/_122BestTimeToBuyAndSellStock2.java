package code;

/**
 * Created by lanjing on 2018/10/11.
 */

import org.junit.Test;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Example 2:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * * 要求：一个给定数组，下标对应的数就是当前天股票的价格，可以无限次数买卖，但是同时只能买进一笔，先买后卖，求最大收益
 * <p>
 * 思路：线性遍历，如果今天的价格比昨天高，那么把差值加入到总利润中，遍历完成即可
 * （画图可知，不用去找最大利润，每次有利润时即进行买卖即可）
 */
public class _122BestTimeToBuyAndSellStock2 {

    public int maxProfit(int[] prices) {
        if (null == prices || prices.length < 1) {
            return 0;
        }
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }

    @Test
    public void run() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
        int[] prices2 = {4, 6, 5, 7, 1, 3, 4};
        System.out.println(maxProfit(prices2));
    }
}
