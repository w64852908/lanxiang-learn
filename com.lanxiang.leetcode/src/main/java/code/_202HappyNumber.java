package code;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lanjing on 2018/12/8.
 * <p>
 * Write an algorithm to determine if a number is "happy".
 * <p>
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Example:
 * <p>
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * <p>
 * 要求：判断一个数是否是happy number
 * <p>
 * 思路：随手写一个数进行测试，可以发现不是happy number的数，经过运算会出现重复元素
 */
public class _202HappyNumber {

    public boolean isHappy(int n) {
        if (n == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        while (true) {
            n = transform(n);
            if (n == 1) {
                return true;
            }
            if (set.contains(n)) {
                return false;
            } else {
                set.add(n);
            }
        }
    }

    private int transform(int n) {
        int res = 0;
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n = n / 10;
        }
        return res;
    }

    @Test
    public void run() {
        int n = 2;
        System.out.println(isHappy(n));
    }
}
