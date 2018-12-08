package code;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lanjing on 2018/11/29.
 */

/**
 * Given an array of integers, find if the array contains any duplicates.
 * <p>
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,1]
 * Output: true
 * Example 2:
 * <p>
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 * <p>
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 * <p>
 * 要求：判断数组是否有重复元素
 * <p>
 * 思路：hash表。。。好久没刷到这么简单的题了
 */
public class _217ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (!set.add(n)) {
                return true;
            }
        }
        return false;
    }

}
