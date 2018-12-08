package code;

/**
 * Created by lanjing on 2018/12/8.
 */

import org.junit.Test;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Example:
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * <p>
 * 要求：给一个数，返回比这个数小的所有非负质数的个数
 * <p>
 * 思路：2是最小的数组，所以从2的倍数来对数组进行标记，标标记的数不是质数
 */
public class _204CountPrimers {

    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }

    @Test
    public void run() {
        int n = 5;
        System.out.println(countPrimes(n));
    }
}
