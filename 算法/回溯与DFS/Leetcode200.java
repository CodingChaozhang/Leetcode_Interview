class Solution {
    public int numIslands(char[][] grid) {
        int count =  0;
        // dfs
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // 如果是岛屿
                if(grid[i][j]=='1'){
                    // 统计数量
                    count++;
                    // dfs将相邻的1标记
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }
    // dfs
    public void dfs(char[][] grid,int i,int j){
        // 递归截止条件
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!='1'){
            return; 
        }
        // 已遍历过的
        grid[i][j] = '2';
        // 遍历其它的
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}