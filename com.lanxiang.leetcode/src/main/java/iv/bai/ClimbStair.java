package iv.bai;

import org.junit.Test;

/**
 * Created by lanjing on 2018/12/6.
 */
public class ClimbStair {

    public int climbStair(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int pre = 1, curr = 1;
        for (int i = 0; i < n; i++) {
            int temp = pre + curr;
            pre = curr;
            curr = temp;
        }
        return curr;
    }

    @Test
    public void run() {
        System.out.println(climbStair(10));
    }
}
