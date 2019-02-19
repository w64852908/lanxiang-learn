package code;

/**
 * Created by lanjing on 2019-02-17.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
 * used and each combination should be a unique set of numbers.
 * <p>
 * Note:
 * <p>
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * 要求：给一个k代表数字个数，一个n代表和，要求k个数字加起来等于n，输出所有可能的排列
 * <p>
 * 思路：全排列问题，dfs，可以看39题
 */
public class _216CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 1 || k < 1 || k > n) {
            return result;
        }
        List<Integer> curPath = new ArrayList<>();
        dfs(n, k, curPath, 1, result);
        return result;
    }

    private void dfs(int n, int k, List<Integer> curPath, int start, List<List<Integer>> result) {
        if (k == curPath.size() && n == 0) {
            result.add(new ArrayList<>(curPath));
            return;
        }
        if (n < 0) {
            return;
        }
        for (int i = start; i <= 9; ++i) {
            curPath.add(i);
            dfs(n - i, k, curPath, i + 1, result);
            curPath.remove(curPath.size() - 1);
        }
    }
}
