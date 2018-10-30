package code;

/**
 * Created by lanjing on 2018/10/28.
 */

import org.junit.Test;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * <p>
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class _091DecodeWays {

    public int numDecodings(String s) {
        if (null == s || s.equals("") || s.startsWith("0")) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        int temp;
        for (int i = 2; i < dp.length; i++) {
            temp = 0;
            //上一位不为0，才可能和这一位组成一个新组合
            if (s.charAt(i - 1) != '0') {
                temp += dp[i - 1];
            }

            if (s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26) {
                temp += dp[i - 2];
            }
            dp[i] = temp;
        }
        return dp[dp.length - 1];
    }

    @Test
    public void run() {
//        String s = "1234";
//        String s = "12";
//        String s = "100";
        String s = "241";
        System.out.println(numDecodings(s));
    }
}
