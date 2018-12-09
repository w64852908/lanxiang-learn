package code;

/**
 * Created by lanjing on 2018/12/9.
 */

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * 要求：给一个数组，数组每个下标代表当前位置能跳到后面的位置，判断是否能跳到数组尾部
 * <p>
 * 思路：用reach来记录当前能跳到最远的位置，每次都判断i + nums[i]是否比reach大，是的话更新。如果reach >= 数组长度，return true
 */
public class _055JumpGame {

    public boolean canJump(int[] nums) {
        if (null == nums || nums.length == 0) {
            return true;
        }
        int len = nums.length - 1;
        int reach = 0;
        for (int i = 0; i <= reach; i++) {
            if (reach < i + nums[i]) {
                reach = i + nums[i];
            }
            if (reach >= len) {
                return true;
            }
        }
        return false;
    }

}
