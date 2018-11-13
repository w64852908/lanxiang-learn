package code;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lanjing on 2018/11/11.
 */

/**
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.
 * <p>
 * Example 1:
 * <p>
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * Example 2:
 * <p>
 * Input:[1, 2, 3, 4, 5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * Example 3:
 * <p>
 * Input: [1, 3, 1, 5, 4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * Note:
 * <p>
 * The pairs (i, j) and (j, i) count as the same pair.
 * The length of the array won't exceed 10,000.
 * All the integers in the given input belong to the range: [-1e7, 1e7].
 * <p>
 * 要求：给一个数组和一个值k，输出数组里面所有一对值，要求一对值的差值等于输入的数值k，并且一对值要求唯一
 * <p>
 * 思路：先排序，然后左右指针求差值，注意要用lastLeft和lastRight记录左右指针上一次指向的位置
 */
public class _532KDiffPairsInAnArray {

    public int findPairs(int[] nums, int k) {
        if (null == nums || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        int lastLeft = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            if (i != 0 && nums[i] == lastLeft) {
                continue;
            }
            int lastRight = nums[i + 1];
            for (int j = i + 1; j < nums.length; j++) {
                if (j != i + 1 && nums[j] == lastRight) {
                    continue;
                }
                int diff = nums[j] - nums[i];
                if (diff > k) {
                    break;
                } else if (diff < k) {
                    continue;
                } else {
                    count++;
                }
                lastRight = nums[j];
            }
            lastLeft = nums[i];
        }
        return count;
    }

    @Test
    public void run() {
        int[] arr = {1, 3, 5, 1, 7, 3, 5};
        System.out.println(findPairs(arr, 2));
    }
}
