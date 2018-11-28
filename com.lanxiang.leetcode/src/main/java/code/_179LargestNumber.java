package code;

/**
 * Created by lanjing on 2018/11/22.
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Example 1:
 * <p>
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * <p>
 * 要求：输入一个数组，返回数组每个元素组合起来的最大情况
 * <p>
 * 思路：其实就是一个数组排序的过程，不过比较的逻辑是比较先后加起来谁更大(remark，活用Arrays.sort、 Collections.sort)
 */
public class _179LargestNumber {

    public String largestNumber(int[] nums) {
        if (null == nums || nums.length == 0) {
            return "";
        }
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strNums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return -1 * s1.compareTo(s2);
            }
        });
        if (strNums[0].startsWith("0")) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (String str : strNums) {
            res.append(str);
        }
        return res.toString();
    }

    @Test
    public void run() {
        int[] arr1 = {10, 2};
        int[] arr2 = {3, 30, 34, 5, 9};
        int[] arr3 = {121, 12};
        int[] arr4 = {0, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr5 = {0, 0};
        int[] arr6 = {824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247};
        System.out.println(largestNumber(arr1));
        System.out.println(largestNumber(arr2));
        System.out.println(largestNumber(arr3));
        System.out.println(largestNumber(arr4));
        System.out.println(largestNumber(arr5));
        System.out.println(largestNumber(arr6));
    }
}
