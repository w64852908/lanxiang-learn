package code;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Created by lanxiang on 2018/12/21.
 */

/**
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 * <p>
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * matrix = [
 * [1,2,3,4],
 * [5,1,2,3],
 * [9,5,1,2]
 * ]
 * Output: True
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 * Example 2:
 * <p>
 * Input:
 * matrix = [
 * [1,2],
 * [2,2]
 * ]
 * Output: False
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 * <p>
 * Note:
 * <p>
 * matrix will be a 2D array of integers.
 * matrix will have a number of rows and columns in range [1, 20].
 * matrix[i][j] will be integers in range [0, 99].
 * <p>
 * Follow up:
 * <p>
 * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 * <p>
 * 要求：输入一个二维数组，判断这个二维数组是不是Toeplitz Matrix
 * <p>
 * 思路：两次斜着遍历也能输出，但是不好写边界条件。用hashmap的话会容易一点
 */
public class _766ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!map.containsKey(i - j)) {
                    map.put(i - j, matrix[i][j]);
                } else {
                    if (matrix[i][j] != map.get(i - j)) return false;
                }
            }
        }
        return true;
    }

    @Test
    public void run() {
        int[] arr1 = {1, 2};
        int[] arr2 = {2, 2};
        int[][] input = {arr1, arr2};
        System.out.println(isToeplitzMatrix(input));
    }
}
