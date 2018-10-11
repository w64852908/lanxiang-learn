package code;

/**
 * Created by lanjing on 2018/10/11.
 */

import org.junit.Test;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 * <p>
 * Input: "race a car"
 * Output: false
 * <p>
 * 要求：给一串字符串，忽略除开字母、数字以外的情况，判断这个字符串是不是回文的
 * 思路：左右指针，如果不合法，移动对应指针，如果合法则判断字符是否相等
 */
public class _123ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (null == s || s.equals("")) {
            return true;
        }
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            char low = arr[left];
            char high = arr[right];
            if (!isLegal(low)) {
                left++;
                continue;
            }
            if (!isLegal(high)) {
                right--;
                continue;
            }
            if (!isEqual(low, high)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isLegal(char a) {
        return ('a' <= a && a <= 'z') || ('A' <= a && a <= 'Z') || ('0' <= a && a <= '9');
    }

    private boolean isEqual(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }

    @Test
    public void run() {
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "race a car";
        String s3 = "0P";
        System.out.println(isPalindrome(s1));
        System.out.println(isPalindrome(s2));
        System.out.println(isPalindrome(s3));
    }
}
