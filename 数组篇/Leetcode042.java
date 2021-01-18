// 接雨水问题
public class Leetcode042 {
	// 难点在于理解如何接雨水，从当前值出发
	class Solution {
	    public int trap(int[] height) {
	    	// 对数组判断
	    	if(height==null || height.length==0) {
	    		return 0;
	    	}
	    	// 0为左边 1为右边
	    	int[][] dp = new int[height.length][2];
	    	dp[0][0] = 0;
	    	dp[height.length-1][1] = 0;
	    	// 遍历得到当前值左右最大的高度 最左边跟最右边都没法存储水
	    	for(int i=1;i<height.length-1;i++) {
	    		// 左边
	    		for(int left=0;left<i;left++) {
	    			dp[i][0] = Math.max(dp[i][0],height[left]);
	    		}
	    		// 右边
	    		for(int right=i+1;right<height.length;right++) {
	    			dp[i][1] = Math.max(dp[i][1], height[right]);
	    		}
	    	}
	    	
	    	// 开始计算
	    	int res = 0;
	    	for(int i=1;i<height.length-1;i++) {
	    		res += Math.max((Math.min(dp[i][0], dp[i][1]))-height[i], 0);
	    	}
	    	return res;
	    }
	    
	    public int trap_2(int[] height) {
	    	// 对数组判断
	    	if(height==null || height.length==0) {
	    		return 0;
	    	}
	    	// 获取当前值的左右最大值
	    	int[] left_max = new int[height.length];
	    	int[] right_max = new int[height.length];
	    	left_max[0] = height[0];
	    	for(int i=1;i<height.length;i++) {
	    		// 当前最大值 是前面的还是一直是
	    		left_max[i] = Math.max(left_max[i-1], height[i]);
	    	}
	    	right_max[height.length-1] = height[height.length-1];
	    	for(int i=height.length-2;i>=0;i--) {
	    		right_max[i] = Math.max(right_max[i+1], height[i]);
	    	}
	    	
	    	
	    	// 开始计算
	    	int res = 0;
	    	for(int i=1;i<height.length-1;i++) {
	    		res += Math.max((Math.min(left_max[i], right_max[i]))-height[i], 0);
	    	}
	    	return res;
	    }
	}
}
