class Solution {
    public int uniquePaths(int m, int n) {
        // 动态规划解题思路
        // 开辟数组
        int[][] dp = new int[m][n];
        // 初始化
        for(int i=0;i<m;i++){
            dp[i][0] = 1;
        }
        for(int j=0;j<n;j++){
            dp[0][j] = 1;
        }
        // 其余值
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                // 转移方程计算总共路径
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        // 最后的结果值
        return dp[m-1][n-1];
    }
}