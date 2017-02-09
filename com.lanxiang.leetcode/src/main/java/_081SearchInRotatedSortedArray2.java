import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lanjing on 2017/2/8.
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Write a function to determine if a given target is in the array.
 * <p>
 * The array may contain duplicates.
 * <p>
 * Subscribe to see which companies asked this question.
 */

public class _081SearchInRotatedSortedArray2 {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (target == nums[mid]) {
                return true;
            }
            //扭转前的序列
            if (nums[left] < nums[mid]) {
                //在纸上画一下是什么情况,target在mid的哪边
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] > nums[mid]) {        //扭转后的序列
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};
        Assert.assertTrue(search(arr, 11));
    }
}
