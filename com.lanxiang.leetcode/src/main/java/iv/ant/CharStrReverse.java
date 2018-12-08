package iv.ant;

import iv.util.ArrayUtil;
import org.junit.Test;

/**
 * Created by lanjing on 2018/12/1.
 * <p>
 * <p>
 * 要求：java实现一个字符数组组成的英语句子倒置，要求只能申请常数级的内存
 * <p>
 * 思路：先反转整个字符数组，然后反转每一个单词即可
 */
public class CharStrReverse {

    public void reverseCharStr(char[] chars) {
        if (null == chars || chars.length == 0) {
            return;
        }
        reverse(chars, 0, chars.length - 1);
        int left = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverse(chars, left, i - 1);
                left = i + 1;
            }
        }
        //最后一个单词后面没有空格，单独再reverse一次
        reverse(chars, left, chars.length - 1);
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
        char[] input = "i love java".toCharArray();
        reverseCharStr(input);
        ArrayUtil.display(input);
    }
}
