package code;

import org.junit.Test;

/**
 * Created by lanjing on 2018/11/19.
 */


/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * 要求：给一个数组，其有一个出现次数超过一半的元素，找到这个元素
 * <p>
 * 思路：遍历这个数组的时候，定义一个count用来计数，这个超过一半的数，它遇到自己就给count加1，遇到不是自己的数，就给count减1，
 * 最后会怎样呢，count肯定大于0呐，因为这个数的个数超过一半。好，进一步的，我们先随便找个数当这个老大（个数超过一半），如果它的
 * 个数不超过一半，就会在相消中时count为0，那么就把它换掉，最后剩下的那个就是，个数超过一半的那个数了。
 */
public class _169MajorityElement {

    public int majorityElement(int[] nums) {
        int element = 0, count = 0;
        for (int n : nums) {
            if (count == 0) {
                element = n;
                count++;
            } else {
                if (n == element) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return element;
    }


    @Test
    public void run() {
        int[] arr = {2, 3, 2, 2, 3, 3, 3};
        System.out.println(majorityElement(arr));
    }

}
