package code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lanjing on 2018/12/8.
 */
public class _039CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == candidates || candidates.length == 0) {
            return result;
        }
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        recursive(candidates, target, 0, temp, result);
        return result;
    }

    private void recursive(int[] candidates, int target, int i, List<Integer> temp, List<List<Integer>> result) {
        if (target == 0) {
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if (target < candidates[j]) {
                return;
            }
            temp.add(candidates[j]);
            recursive(candidates, target - candidates[j], j, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void run() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }
}
