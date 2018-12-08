package code;

/**
 * Created by lanjing on 2018/12/8.
 */
public class _200NumberOfIslands {

    public int numIslands(char[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    total++;
                    markZero(grid, i, j);
                }
            }
        }
        return total;
    }

    private void markZero(char[][] grid, int i, int j) {
        if (i > grid.length || j > grid[i].length) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        if (i + 1 > grid.length && grid[i + 1][j] == '1') {
            markZero(grid, i + 1, j);
        }
        if (j + 1 > grid[i].length && grid[i][j + 1] == '1') {
            markZero(grid, i, j + 1);
        }
        grid[i][j] = '0';
    }


}
