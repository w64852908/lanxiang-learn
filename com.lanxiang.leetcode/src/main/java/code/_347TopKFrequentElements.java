package code;

import org.junit.Test;

import java.util.*;

/**
 * Created by lanjing on 2018/12/15.
 */

/**
 * 347. Top K Frequent Elements
 * Medium
 * <p>
 * 1122
 * <p>
 * 70
 * <p>
 * Favorite
 * <p>
 * Share
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * <p>
 * 要求：给一个数组，要求输出出现频次topk的数字
 * <p>
 * 思路：先用map统计频次，然后用List把所有entry加入进来，然后用comparator排序entry
 * <p>
 * 注意：TreeMap的comparator只能排序key
 */
public class _347TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (null == nums || nums.length == 0 || k <= 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                map.put(n, map.get(n) + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.size());
        entryList.addAll(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return -1 * o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<Integer, Integer> entry : entryList) {
            res.add(entry.getKey());
            if (--k == 0) {
                break;
            }
        }
        return res;
    }

    @Test
    public void run() {
        int[] arr = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(topKFrequent(arr, 2));
    }
}
