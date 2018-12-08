package code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2018/12/8.
 */

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * <p>
 * Examples:
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 * <p>
 * 要求：找到一个字符串里，第一个不重复的字符
 * <p>
 * 思路：定一个size为26的数组，每个位置存储当前字符出现的次数，遍历两次字符串，一次统计一次取最小
 */
public class _387FirstUniqueCharacterinaString {

    public int firstUniqChar(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            ++count[c - 'a'];
        }
        for (int i = 0; i < chars.length; i++) {
            if (count[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

}
