package code;

/**
 * Created by lanjing on 2018/11/12.
 */

import org.junit.Test;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,4,5,1,2]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 * <p>
 * 要求：一个升序数组，在某一个节点被扭转了，要求找到扭转这个位置（输出最小值）。可以认为数组没有重复元素
 * <p>
 * 思路：和归并排序的思路一致，把数组每次按中位拆成两份，每次比较
 */
public class _153FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return mergeCompare(nums, 0, nums.length - 1);
    }

    private int mergeCompare(int[] nums, int start, int end) {
        //如果归并时，start和middle相等或者大于，则终止递归
        if (start >= end) {
            return Math.min(nums[start], nums[end]);
        }
        //数组只剩两个元素时，判断扭转点是否在此，并终止递归
        if (end - start == 1) {
            if (nums[start] > nums[end]) {
                return nums[end];
            } else {
                return Math.min(nums[start], nums[end]);

            }
        }
        //数组中位点
        int middle = getMiddle(start, end);
        //如果中位点已经小于start或者大于end，则终止
        if (middle < start || middle > end) {
            return Math.min(nums[start], nums[end]);
        }
        //如果中位点就是扭转点
        if (nums[middle] > nums[middle + 1]) {
            return nums[middle + 1];
        }
        return Math.min(mergeCompare(nums, start, middle), mergeCompare(nums, middle + 1, end));
    }

    private int getMiddle(int start, int end) {
        return (end + start) / 2;
    }

    public int findMin2(int[] num) {
        int start = 0;
        int end = num.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (num[end] == num[mid]) {
                return num[mid];
            }
            if (num[end] < num[mid]) {
                start = mid + 1;
            }
            if (num[end] > num[mid]) {
                end = mid;
            }
        }
        return -1;
    }

    @Test
    public void run() {
        int[] arr = {3, 4, 5, 1, 2};
//        int[] arr = {4, 5, 6, 7, 0, 1, 2};
//        int[] arr = {1, 2, 3, 4, 5, 6};
//        int[] arr = {3, 1, 2};
//        int[] arr = {3, 4, 5, 6, 1, 2};
        System.out.println(findMin2(arr));
    }
}
