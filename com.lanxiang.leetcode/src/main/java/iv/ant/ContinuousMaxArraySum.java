package iv.ant;

/**
 * Created by lanjing on 2018/12/10.
 */

import org.junit.Test;

/**
 * 要求：找到数组中连续的元素和的最大值
 * <p>
 * 思路：max记录最大和，curr记录当前的和，如果curr<0,把curr=0
 */
public class ContinuousMaxArraySum {

    public int getContinuousMaxArraySum(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int max = 0, curr = 0;
        for (int i = 0; i < arr.length; i++) {
            curr += arr[i];
            if (curr < 0) {
                curr = 0;
            }
            max = Math.max(max, curr);
        }
        return max;
    }

    @Test
    public void run() {
        int[] arr = {1, 2, 3, 4, -11, 4, 7, -2};
        System.out.println(getContinuousMaxArraySum(arr));
    }
}
