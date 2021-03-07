// 一维数组的动态和
public class Leetcode1480 {
	class Solution {
	    public int[] runningSum(int[] nums) {
	    	int[] dp = new int[nums.length];
	    	// 对其遍历
	    	int res = nums[0];
	    	dp[0]   = nums[0];
	    	for(int i=1;i<nums.length;i++) {
	    		res += nums[i];
	    		dp[i] = res;
	    	}
	    	return dp;
	    }
	}
}
