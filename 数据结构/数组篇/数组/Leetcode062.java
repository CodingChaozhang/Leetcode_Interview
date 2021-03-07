// 动态规划 定义 初始化 转移方程
public class Leetcode062 {
	class Solution {
	    public int uniquePaths(int m, int n) {
	            // 从上往下走
		    	int[][] dp = new int[m][n];
		    	// 初始化
		    	for(int i=0;i<m;i++) {
		    		dp[i][0] = 1;
		    	}
		    	for(int j=0;j<n;j++) {
		    		dp[0][j] = 1;
		    	}
		    	// 开始遍历
		    	for(int i=1;i<m;i++) {
		    		for(int j=1;j<n;j++) {
		    			// 转移方程 求路径和
		    			dp[i][j] = dp[i-1][j] + dp[i][j-1];
		    		}
		    	}
		    	
		    	return dp[m-1][n-1];
	    }
	}
}
