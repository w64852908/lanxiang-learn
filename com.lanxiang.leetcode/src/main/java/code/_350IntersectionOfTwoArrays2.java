package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanjing on 2018/12/15.
 */

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 * <p>
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * <p>
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements
 * into the memory at once?
 * <p>
 * 要求：给两个数组，输出两个数组的交集
 * <p>
 * 思路：用一个hashmap存储其中一个数组的情况
 * 需要注意：交集元素有重复的话，需要输出重复元素。
 */
public class _350IntersectionOfTwoArrays2 {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (null == nums1 || null == nums2) {
            return null;
        }
        int[] bigger = nums1.length > nums2.length ? nums1 : nums2;
        int[] smaller = nums1.length > nums2.length ? nums2 : nums1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int s : smaller) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        List<Integer> intersection = new ArrayList<>();
        for (int b : bigger) {
            if (map.containsKey(b)) {
                intersection.add(b);
                int count = map.get(b) - 1;
                if (count == 0) {
                    map.remove(b);
                } else {
                    map.put(b, count);
                }
            }
        }
        int[] res = new int[intersection.size()];
        int count = 0;
        for (Integer i : intersection) {
            res[count++] = i;
        }
        return res;
    }
}
