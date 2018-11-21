package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/17.
 */

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * Example 1:
 * <p>
 * Input: 1
 * Output: "A"
 * Example 2:
 * <p>
 * Input: 28
 * Output: "AB"
 * Example 3:
 * <p>
 * Input: 701
 * Output: "ZY"
 * <p>
 * 要求：给一串数字，输出对应规律的字符串
 * <p>
 * 思路：找规律，相当于26进制的转换
 * <p>
 * 需要注意的是：要得到字符A、B、C等，只需要用0、1、2去加'A'即可
 */
public class _168ExcelSheetColumnTitle {

    public String convertToTitle(int n) {
        if (n < 1) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            res.insert(0, (char) ((n - 1) % 26 + 'A'));
            n = (n - 1) / 26;
        }
        return res.toString();
    }

    @Test
    public void run() {
        int i = 701;
        System.out.println(convertToTitle(i));
        System.out.println((char) ('A' + 1));
    }
}
