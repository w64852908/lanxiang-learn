package code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2018/10/12.
 */

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * <p>
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * <p>
 * Example:
 * <p>
 * Input: 5
 * Output:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * <p>
 * 要求：给出层数，层次遍历对应的杨辉三角形
 * <p>
 * 思路：画图，把三角形向左对齐，然后找规律
 */
public class _118PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {
            return result;
        }
        int level = 1;
        //上一层
        List<Integer> last = new ArrayList<>();
        //当前一层
        List<Integer> curr;
        //输出的三角形和input的层数一致
        while (level <= numRows) {
            curr = new ArrayList<>();
            int i = 0;
            //第几层就有几个元素
            while (i < level) {
                //第一个和最后一个元素是1
                if (i == 0 || i == level - 1) {
                    curr.add(1);
                    //和上一层元素有关
                } else {
                    curr.add(last.get(i - 1) + last.get(i));
                }
                i++;
            }
            result.add(curr);
            last = curr;
            level++;
        }
        return result;
    }

    @Test
    public void run() {
        int level = 5;
        System.out.println(generate(level));
    }
}
