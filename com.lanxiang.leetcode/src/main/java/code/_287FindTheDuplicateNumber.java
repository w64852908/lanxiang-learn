package code;

/**
 * Created by lanjing on 2018/12/15.
 */

import org.junit.Test;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least
 * one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 * <p>
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * <p>
 * 要求：给一个数组，找出数组中唯一一个出现重复的数字
 * 不能修改数组、只能使用常数级内存、时间复杂度要小于O(n2)
 * <p>
 * 思路：
 * <p>
 * 解法1：
 * 比如数组是213,则映射关系为0->2, 1->1, 2->3。假设这个一对一映射关系是一个函数f(n)，其中n是下标，f(n)是映射到的数。如果我们从下标为0
 * 出发，根据这个函数计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推，直到下标超界。实际上可以产生一个类似链表一样的序列。
 * 比如在这个例子中有两个下标的序列，0->2->3。
 * 但如果有重复的话，这中间就会产生多对一的映射，比如数组2131,则映射关系为0->2, {1，3}->1, 2->3。这样，我们推演的序列就一定会有环路了，
 * 这里下标的序列是0->2->3->1->1->1->1->...，而环的起点就是重复的数。
 * 所以该题实际上就是找环路起点的题，和Linked List Cycle II一样。我们先用快慢两个下标都从0开始，快下标每轮映射两次，慢下标每轮映射一次，
 * 直到两个下标再次相同。这时候保持慢下标位置不变，再用一个新的下标从0开始，这两个下标都继续每轮映射一次，当这两个下标相遇时，就是环的起点，
 * 也就是重复的数
 * <p>
 * 解法2：
 * 二分法
 * 我们不一定要依次选择数，然后看是否有这个数的重复数，我们可以用二分法先选取n/2，按照抽屉原理，整个数组中如果小于等于n/2的数的数量大于n/2，
 * 说明1到n/2这个区间是肯定有重复数字的。比如6个抽屉，如果有7个袜子要放到抽屉里，那肯定有一个抽屉至少两个袜子。这里抽屉就是1到n/2的每一个
 * 数，而袜子就是整个数组中小于等于n/2的那些数。这样我们就能知道下次选择的数的范围，如果1到n/2区间内肯定有重复数字，则下次在1到n/2范围内
 * 找，否则在n/2到n范围内找。下次找的时候，还是找一半。
 * 注意：
 * 我们比较的mid而不是nums[mid]
 * 因为mid是下标，所以判断式应为cnt > mid，最后返回min
 */
public class _287FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length && nums[fast] < nums.length) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }
        int find = 0;
        while (slow != find) {
            slow = nums[slow];
            find = nums[find];
        }
        return find;
    }

    public int findDuplicate2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    @Test
    public void run() {
//        int[] arr = {1, 3, 4, 2, 2};
//        int[] arr = {3, 1, 3, 4, 2};
        int[] arr = {2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
        System.out.println(findDuplicate2(arr));
    }
}
