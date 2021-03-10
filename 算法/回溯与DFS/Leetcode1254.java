class Solution {
    public int closedIsland(int[][] grid) {
        // 访问
        // 孤岛
        int res=0;
        // 判断true或者false
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // 判断 0附近都是1
                if(grid[i][j]==0&&dfs(grid,i,j)){
                    res++;
                }
            }
        }
        return res;
    }

    // 孤岛
    public boolean dfs(int[][] grid,int i,int j){
        // 越界肯定不是
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length){
            return false;
        }
        // 为岛屿
        if(grid[i][j]!=0){
            return true;
        }
        // 记录访问过的
        grid[i][j] = 2;
        // 看别的
        boolean left = dfs(grid,i+1,j);
        boolean right = dfs(grid,i-1,j);
        boolean up = dfs(grid,i,j+1);
        boolean down = dfs(grid,i,j-1);
        return left&&right&&up&&down;
    }
}