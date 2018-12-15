package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/12/15.
 */

/**
 * Write a function that takes a string as input and returns the string reversed.
 * <p>
 * Example 1:
 * <p>
 * Input: "hello"
 * Output: "olleh"
 * Example 2:
 * <p>
 * Input: "A man, a plan, a canal: Panama"
 * Output: "amanaP :lanac a ,nalp a ,nam A"
 * <p>
 * 要求：倒置字符串
 * <p>
 * 思路：左右指针交换char
 */
public class _344ReverseString {

    public String reverseString(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        //最后一个单词后面没有空格，单独再reverse一次
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int left, int right) {
        if (null == chars || chars.length == 0 || left < 0 || right > chars.length - 1) {
            return;
        }
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    @Test
    public void run() {
        String s = "hello";
        System.out.println(reverseString(s));
    }
}
