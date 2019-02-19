package code;

/**
 * Created by lanjing on 2019/2/7.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * 要求：判断两个字符串是不是同样方式构成的
 *
 * 思路：遍历两个字符串，比较每个字符的位置出现是否一致
 */
public class _205IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        List<Character> listS = new ArrayList<>();
        List<Character> listT = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            int sIndex = listS.indexOf(s.charAt(i));
            int tIndex = listT.indexOf(t.charAt(i));
            if (sIndex != tIndex) {
                return false;
            }
            listS.add(s.charAt(i));
            listT.add(t.charAt(i));
        }

        return true;
    }

}
