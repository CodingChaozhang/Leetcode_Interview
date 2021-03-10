class Solution {
    public int islandPerimeter(int[][] grid) {
        // 统计边界数量 边界=岛屿与外
        int count = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    // 从陆地开始找
                    count += dfs(grid,i,j);
                }
            }
        }
        return count;
    }

    // 对其dfs
    public int dfs(int[][] grid,int i,int j){
        // 递归截止条件 有一条边界
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==0){
            return 1;
        }
        // 遇到重复的
        if(grid[i][j]==2){
            return 0;
        }
        grid[i][j]=2;

        // 其余
        int left = dfs(grid,i+1,j);
        int right = dfs(grid,i-1,j);
        int down = dfs(grid,i,j-1);
        int up = dfs(grid,i,j+1);

        return left+right+down+up;

    }
}