package code;

/**
 * Created by lanjing on 2018/11/16.
 */

import org.junit.Test;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 * <p>
 * Note:
 * <p>
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 * <p>
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * <p>
 * 要求：two sum问题，数组是有序的
 * <p>
 * 思路：O(n)解法，左右指针，遍历至和为target为止
 * 或者使用二分的解法，时间复杂度是O(nlgn)
 */
public class _167TwoSum2InputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        if (null == numbers || numbers.length < 2) {
            return null;
        }
        int[] res = new int[2];
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    @Test
    public void run() {
        int[] arr = {2, 5, 7, 11, 15};
        System.out.println(twoSum(arr, 9)[0]);
    }
}
