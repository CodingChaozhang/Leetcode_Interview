class Solution {
    public int movingCount(int m, int n, int k) {
        // 解题思路：运动范围dfs 上下左右
        // 建立一个方格
        int[][] matrix = new int[m][n];
        // 已遍历到的
        boolean[][] visited = new boolean[m][n];
        return dfs(matrix,visited,0,0,k);
    }

    // 回溯
    public int dfs(int[][] matrix,boolean[][] visited,int i,int j,int k){
        // 递归结束 已遍历到 以及 题意
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[0].length || visited[i][j] || (i/10+i%10 + j/10+j%10)>k){
            return 0;
        }
        // 已遍历到的便是遍历到了 不需要再返回
        visited[i][j] = true;
        
        return 1 + dfs(matrix,visited,i+1,j,k) + dfs(matrix,visited,i-1,j,k) 
                 + dfs(matrix,visited,i,j+1,k) + dfs(matrix,visited,i,j-1,k);
       
    }
}