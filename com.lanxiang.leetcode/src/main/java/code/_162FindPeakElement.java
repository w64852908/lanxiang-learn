package code;

/**
 * Created by lanjing on 2018/11/15.
 */

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 * Note:
 * <p>
 * Your solution should be in logarithmic complexity.
 * <p>
 * 要求：找到数组的局部最大值，如果有多个局部最大值，返回第一个就行
 * <p>
 * 思路：从第二个元素开始遍历，如果这个元素比上一个元素小，那么上一个元素就是局部最大值。否则最后一个值就是局部最大值
 */
public class _162FindPeakElement {

    public int findPeakElement(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return i - 1;
            }
        }
        return nums.length - 1;
    }
}
