package code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2018/10/16.
 */

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * <p>
 * Note that the row index starts from 0.
 * <p>
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 * <p>
 * Could you optimize your algorithm to use only O(k) extra space?
 * <p>
 * 要求：输出给定层数k的杨辉三角形数据，最多只使用O(k)的额外空间
 * 思路：只申请一个和层数一样大的数组，每遍历到一层，先往数组首位加一位1，然后从第二位开始更新到倒数第二位，每一位都更新为当前值+当前值的后一位数
 * 画图能更直观的想清楚
 */
public class _119PascalsTriangle2 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            result.add(0, 1);
            for (int j = 1; j < result.size() - 1; j++) {
                result.set(j, result.get(j) + result.get(j + 1));
            }
        }
        return result;
    }

    @Test
    public void run() {
        int level = 4;
        System.out.println(getRow(level));
    }
}
