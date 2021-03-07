// 使用最小花费爬楼梯
public class Leetcode746 {
	class Solution {
		// 动态规划解题 定义 初始化 转移方程
	    public int minCostClimbingStairs(int[] cost) {
	    	int[] dp = new int[cost.length];
	    	//初始化
	    	dp[0] = cost[0];
	    	dp[1] = cost[1];
	    	// 不一定非得到最后一个 倒数第二个也可以
	    	for(int i=2;i<cost.length;i++) {
	    		// 当前值=前一家 前两家
	    		dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
	    	}
	    	//返回值
	    	return Math.min(dp[cost.length-1], dp[cost.length-2]);
	    }
	}
}
