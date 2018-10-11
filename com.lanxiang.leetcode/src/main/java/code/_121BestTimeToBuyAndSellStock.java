package code;

/**
 * Created by lanjing on 2018/10/10.
 */

import org.junit.Test;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * 要求：一个给定数组，下标对应的数就是当前天股票的价格，只能买一次卖一次，先买后卖，求最大收益
 * <p>
 * 思路：4个变量，最小值下标、最大值下标 最小值 最大值
 * 线性遍历，当前值小于值时，更新最小值和最小值下标，当前值大于最大值时||当前最大值下标小于最小值下标时，更新最大值和最大值下标
 * 遍历过程中，如果最小值下标小于最大值下标，比对此时的profit和最大profit
 */
public class _121BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        if (null == prices || prices.length < 1) {
            return 0;
        }
        int profit = 0;
        //记录最小值的下标
        int minIndex = 0;
        //记录最大值的下标
        int maxIndex = 0;
        //最小值
        int min = prices[0];
        //最大值
        int max = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
                minIndex = i;
            } else if (prices[i] > max || maxIndex < minIndex) {
                max = prices[i];
                maxIndex = i;
            }
            if (maxIndex > minIndex) {
                profit = max - min > profit ? max - min : profit;
            }
        }
        return profit;
    }

    @Test
    public void run() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

}
