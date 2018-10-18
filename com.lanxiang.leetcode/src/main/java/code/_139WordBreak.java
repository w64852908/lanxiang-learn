package code;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lanjing on 2018/10/18.
 * <p>
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * <p>
 * 要求：给一个字符串，判断字符串是否能被词典里的单词组成
 * <p>
 * 思路：单词拆分问题，典型的动态规划
 * <p>
 * 设dp[i]为前i个字符是否可以切割。
 * <p>
 * 一个字符串S，它的长度为len，如果S能够被“字典集合”（dict）中的单词拼接而成，那么所要满足的条件为：
 * dp[j] && dict.contains(s.substring(j, i))
 * 如果我们想知道某个子串是否可由dict中的几个单词拼接而成就可以用这样的方式得到结果（满足条件为True, 不满足条件为False）存入到一个boolean数组的对应位置上
 */
public class _139WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        //dp[i] 表示前i个字符能不能被dict完美划分
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                //i = 4时，循环时temp 为 appl ppl pl l
                String temp = s.substring(j, i);
                //dp[j]快速过滤，因为之前的循环记录了可以分割的位置，不可分割的话dp[j]=false。dp[0]默认为true
                if (dp[j] && wordDict.contains(temp)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    @Test
    public void run() {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");
        System.out.println(wordBreak(s, wordDict));
    }
}
