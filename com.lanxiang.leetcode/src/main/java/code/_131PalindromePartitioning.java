package code;

/**
 * Created by lanjing on 2018/11/5.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example:
 * <p>
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class _131PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> temp = new ArrayList<String>();
        dfs(s, 0, temp, result);
        return result;
    }

    public void dfs(String s, int curr, List<String> temp, List<List<String>> result) {
        if (curr == s.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = curr + 1; i <= s.length(); i++) {
            String prefix = s.substring(curr, i);
            if (!isPalindrome(prefix)) {
                continue;
            }
            temp.add(prefix);
            dfs(s, i, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Test
    public void run() {
        String s = "aab";
        System.out.println(partition(s));
    }
}
