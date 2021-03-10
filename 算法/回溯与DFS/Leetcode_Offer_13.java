class Solution {
    public int movingCount(int m, int n, int k) {
        // 建立方格
        int[][] square = new int[m][n];
        // 从(0,0)开始走 计算方格
        int count = dfs(square,0,0,k);
        return count;

    }
    // dfs
    public int dfs(int[][] square,int i,int j,int k){
        // 返回值走不了 走过了
        if(i<0 || i>=square.length || j<0 || j>=square[0].length || (i%10+i/10+j%10+j/10)>(k) || square[i][j]==1){
            return 0;
        }


        // 走过当前格子了
        square[i][j] = 1;

        //当前格子
        int left = dfs(square,i+1,j,k);
        int right = dfs(square,i-1,j,k);
        int up  = dfs(square,i,j+1,k);
        int down = dfs(square,i,j-1,k);


        return 1+left+right+up+down;
    } 
}