class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        // dfs统计最大数量
        int max_count = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // 如果为1则开始统计
                if(grid[i][j]==1){
                    max_count = Math.max(max_count,dfs(grid,i,j));
                }
            }
        }
        return max_count;
    }

    // 统计与其相邻的1的个数
    public int dfs(int[][] grid,int i,int j){
        // 遇到已访问过的 统计要注意已访问过的
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0){
            return 0;
        }

        // 已访问
        grid[i][j]=0;
        // 遍历其它
        int count = 1;
        int left = dfs(grid,i-1,j);
        int right = dfs(grid,i+1,j);
        int up = dfs(grid,i,j-1);
        int down = dfs(grid,i,j+1);
        return count+left+right+up+down;

    }
}