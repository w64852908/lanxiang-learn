package code;

/**
 * Created by lanjing on 2018/12/16.
 */

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should:
 * <p>
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: true
 * Example 2:
 * <p>
 * Input: [5,4,3,2,1]
 * Output: false
 * <p>
 * 要求：给一个数组，要求判断数组里是否存在三个递增的元素
 * <p>
 * 思路：用一个small，big来存储经历过的元素。
 * 如果n<=small，更新small，如果元素比small和big都小，更新big，如果元素大于small但是小于big，那么return true
 */
public class _334IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        if (null == nums || nums.length < 3) {
            return false;
        }
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) {
                small = n;
            }

            // update small if n is smaller than both
            else if (n <= big) {
                big = n;
            }
            // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }
}
