// 最大子序和
public class Leetcode053 {
	class Solution {
	    public int maxSubArray(int[] nums) {
	    	// 动态规划解题 定义 初始化 转移方程
	    	int[] dp = new int[nums.length+1];
	    	dp[0]    = nums[0];
	    	for(int i=1;i<nums.length;i++) {
	    		// 转移方程 当前值 和 当前值以及前一个的值
	    		dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
	    	}
	    	// 从数组中寻找最大值
	    	int max_value = dp[0];
	    	for(int i=1;i<nums.length;i++) {
	    		max_value = Math.max(max_value, dp[i]);
	    	}
	    	return max_value;
	    }
	}
}
