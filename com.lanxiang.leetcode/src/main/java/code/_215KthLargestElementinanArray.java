package code;

/**
 * Created by lanjing on 2018/12/9.
 */

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * <p>
 * 要求：返回数组里面第k大的元素
 * <p>
 * 思路：快排解决的topk问题，只是这里要的是最大的，还有就是k-1才是数组下标
 */
public class _215KthLargestElementinanArray {

    public int findKthLargest(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k > nums.length) {
            return 0;
        }
        int mid = divide(nums, 0, nums.length - 1);
        while (k - 1 != mid) {
            if (k - 1 > mid) {
                mid = divide(nums, mid + 1, nums.length - 1);
            } else {
                mid = divide(nums, 0, mid - 1);
            }
        }
        return nums[k - 1];
    }

    private int divide(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] <= temp) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] >= temp) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = temp;
        return left;
    }

}
