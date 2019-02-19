package code;

/**
 * Created by lanjing on 2019-02-18.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in
 * the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * <p>
 * 要求：给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，
 * 并且 i 和 j 的差的绝对值最大为 k。
 * <p>
 * 思路：先用map，记录所有出现过重复的值，和他们的下标。遍历所有元素个数大于2的数组，查看是否有相邻元素的index差小于k
 */
public class _219ContainsDuplicate2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k <= 0) {
            return false;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (map.containsKey(n)) {
                map.get(n).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(n, list);
            }
        }
        for (List<Integer> list : map.values()) {
            if (list.size() < 2) {
                continue;
            }
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i + 1) - list.get(i) <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void run() {
        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;

        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;

        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;
        System.out.println(containsNearbyDuplicate(nums1, k1));
        System.out.println(containsNearbyDuplicate(nums2, k2));
        System.out.println(containsNearbyDuplicate(nums3, k3));
    }
}
