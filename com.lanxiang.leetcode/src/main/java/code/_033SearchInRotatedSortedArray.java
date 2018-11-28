package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/28.
 */

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * <p>
 * 要求：给一个被扭转的有序数组和一个target，要求找到target的位置
 * <p>
 * 思路：
 * <p>
 * <p>
 * 这道题让在旋转数组中搜索一个给定值，若存在返回坐标，若不存在返回-1。我们还是考虑二分搜索法，但是这道题的难点在于我们不知道原数组在哪旋转
 * 了，我们还是用题目中给的例子来分析，对于数组[0 1 2 4 5 6 7] 共有下列七种旋转方法：
 * <p>
 * 0　　1　　2　　 4　　5　　6　　7
 * <p>
 * 7　　0　　1　　 2　　4　　5　　6
 * <p>
 * 6　　7　　0　　 1　　2　　4　　5
 * <p>
 * 5　　6　　7　　 0　　1　　2　　4
 * <p>
 * 4　　5　　6　　7　　0　　1　　2
 * <p>
 * 2　　4　　5　　6　　7　　0　　1
 * <p>
 * 1　　2　　4　　5　　6　　7　　0
 * <p>
 * 二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段，我们观察上面红色的数字都是升序的，由此我们可以观察出规律，
 * 如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的，我们只要在有序的半段里用首尾两个数组来判断
 * 目标值是否在这一区域内，这样就可以确定保留哪半边了
 * <p>
 * key: 比较mid和right的值，可以判断mid的左边有序还是右边有序
 */
public class _033SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            //如果mid比right小，说明右边有序
            if (nums[middle] < nums[right]) {
                if (target > nums[middle] && nums[right] >= target) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else {
                //mid比right小，说明左边有序
                if (target < nums[middle] && nums[left] <= target) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void run() {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(search(arr, target));
    }
}
