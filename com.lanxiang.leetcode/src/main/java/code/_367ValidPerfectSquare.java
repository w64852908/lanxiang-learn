package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/12/16.
 * <p>
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * <p>
 * Note: Do not use any built-in library function such as sqrt.
 * <p>
 * Example 1:
 * <p>
 * Input: 16
 * Output: true
 * Example 2:
 * <p>
 * Input: 14
 * Output: false
 * <p>
 * 要求：给一个数，判断这个数是否是某个数的平方
 * <p>
 * 思路：和开根号那个题一个思路，都是用二分的思想
 */
public class _367ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        if (num == 0) {
            return true;
        }
        long left = 0, right = num;
        while (left <= right) {
            long mid = (left + right) / 2;
            long cal = mid * mid;
            if (cal < num) {
                left = mid + 1;
            } else if (cal > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ":" + isPerfectSquare(i));
        }
    }
}
