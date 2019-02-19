package code;

/**
 * Created by lanjing on 2018/12/8.
 */

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * Output: 1
 * Example 2:
 * <p>
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * Output: 3
 * <p>
 * 要求：给一个二维数组，1代表岛屿，0代表海洋，求岛屿的个数
 * <p>
 * 思路：dfs深度优先遍历，用一个二维数组标记所有去过的点，然后递归时需要往x、y，上下左右4个方向移动递归
 */
public class _200NumberOfIslands {

    public int numIslands(char[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        boolean[][] isMarked = new boolean[grid.length][grid[0].length];
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !isMarked[i][j]) {
                    bfsAndMark(grid, isMarked, i, j);
                    total++;
                }
            }
        }
        return total;
    }

    private void bfsAndMark(char[][] grid, boolean[][] isMarked, int row, int column) {
        if (row < 0 || row >= grid.length) {
            return;
        }
        if (column < 0 || column >= grid[0].length) {
            return;
        }
        if (grid[row][column] != '1' || isMarked[row][column]) {
            return;
        }
        isMarked[row][column] = true;
        bfsAndMark(grid, isMarked, row - 1, column);
        bfsAndMark(grid, isMarked, row + 1, column);
        bfsAndMark(grid, isMarked, row, column - 1);
        bfsAndMark(grid, isMarked, row, column + 1);
    }
}
