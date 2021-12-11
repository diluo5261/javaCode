
/*
给一个01矩阵，1代表是陆地，0代表海洋， 如果两个1相邻，那么这两个1属于同一个岛。我们只考虑上下左右为相邻。
岛屿: 相邻陆地可以组成一个岛屿（相邻:上下左右） 判断岛屿个数。
*/


import java.util.*;


public class Solution {
    /**
     * 判断岛屿数量
     * @param grid char字符型二维数组 
     * @return int整型
     */
    public int solve (char[][] grid) {
        // write code here
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {    
                    count++;            //若出现 1，则代表这是一个岛屿
                    dfs(grid, i, j);    //若遍历过以后，这个区域都改为 2
                }
            }
        }
        return count;
    }
    
    public void dfs(char[][] grid, int r, int c) {
        if (!inArae(grid, r, c)) return;    //判断是否在区域内
        if (grid[r][c] != '1') return;      //如果这个格子不是岛屿（0 或 2），直接返回

        grid[r][c] = '2';   // 将格子标记为「已遍历过」

        // 访问上、下、左、右四个相邻结点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    public boolean inArae(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length 
            && 0 <= c && c < grid[0].length;
    }
}
