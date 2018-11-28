package code;

import org.junit.Test;
import properties.ArrayOutputUtil;

/**
 * Created by lanjing on 2018/11/26.
 */

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * <p>
 * Input: [-1,-100,3,99] and k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * Note:
 * <p>
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * <p>
 * 要求：输入一个数组，要求在k下标扭转数组，把k之后的元素放到最前面
 * <p>
 * 思路：翻手指的解法，例如1，2，3，4，5，6，k = 2
 * 1.先把数组分为1，2，3，4 和 5，6
 * 2.分别翻转两个子数组，得到4，3，2，1 和 6，5
 * 3.翻转整个数组，得到5，6，1，2，3，4，即为目标值
 * <p>
 * 注意：如果k的长度比数组还要大，那么对k取mod操作
 */
public class _189RotateArray {

    public void rotate(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k == 0) {
            return;
        }
        if (k > nums.length) {
            k = k % nums.length;
        }
        reverse(nums, 0, nums.length - 1 - k);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        if (null == nums || left < 0 || right > nums.length - 1) {
            return;
        }
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    @Test
    public void run() {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[] arr = {1, 2, 3};
        rotate(arr, 4);
        ArrayOutputUtil.printArray(arr);
    }
}
