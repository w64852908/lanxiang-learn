package code;

/**
 * Created by lanjing on 2018/10/24.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * <p>
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * <p>
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * Output: 16
 * <p>
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 * <p>
 * 要求：给一个二维数组，是1的位置代表一个小岛，求整个岛的周长（一个岛代表一个正方形）
 * <p>
 * 思路：一个岛的周长是4，如果这个岛上面有岛，则周长-2，如果这个岛旁边有岛，则周长再-2，遍历sum求得
 */
public class _463IslandPerimeter {

    public int islandPerimeter(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int res = 0;
//        int[] last = new int[grid[0].length];
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                int curr = grid[i][j];
//                if (curr == 1) {
//                    res += 4;
//                    if (last[j] == 1) {
//                        res -= 2;
//                    }
//                    if (j > 0 && grid[i][j - 1] == 1) {
//                        res -= 2;
//                    }
//                }
//                last[j] = curr;
//            }
//        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int curr = grid[i][j];
                if (curr == 1) {
                    res += 4;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        res -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        res -= 2;
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void run() {
//        int[] arr1 = {0, 1, 0, 0};
//        int[] arr2 = {1, 1, 1, 0};
//        int[] arr3 = {0, 1, 0, 0};
//        int[] arr4 = {1, 1, 0, 0};
//        int[][] arr = {arr1, arr2, arr3, arr4};

//        int[] arr1 = {1, 1};
//        int[][] arr = {arr1};

//        int[] arr1 = {0};
//        int[] arr2 = {1};
//        int[][] arr = {arr1, arr2};

        int[] arr1 = {1, 1, 1};
        int[] arr2 = {1, 0, 1};
        int[][] arr = {arr1, arr2};
        System.out.println(islandPerimeter(arr));
    }
}
