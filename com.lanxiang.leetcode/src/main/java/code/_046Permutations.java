package code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2018/12/8.
 */
public class _046Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return res;
        }
        recursive(nums, 0, res);
        return res;
    }

    private void recursive(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int n : nums) {
                temp.add(n);
            }
            res.add(temp);
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            recursive(nums, index + 1, res);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    @Test
    public void run() {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
}
