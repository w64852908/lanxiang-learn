package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/20.
 */

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * <p>
 * For example:
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * Example 1:
 * <p>
 * Input: "A"
 * Output: 1
 * Example 2:
 * <p>
 * Input: "AB"
 * Output: 28
 * Example 3:
 * <p>
 * Input: "ZY"
 * Output: 701
 * <p>
 * 要求：把一串字符串按规则转换成数字，和168题反过来
 * <p>
 * 思路：26进制转10进制的感觉 char - 'A' 即可得到字符对应的数字
 */
public class _171ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int res = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[arr.length - 1 - i];
            res = res + (c - 'A' + 1) * (int) Math.pow(26, i);
        }
        return res;
    }

    @Test
    public void run() {
        String s = "AAA";
        System.out.println(titleToNumber(s));
    }
}
