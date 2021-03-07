// 删掉一个元素以后全为1的最长子数组
public class Leetcode1493 {
	class Solution {
	    public int longestSubarray(int[] nums) {
	    	// 删掉一个元素以后全为1的最长子数组
	    	// 解题思路：通过滑动窗口来解题
	    	int l = 0;
	    	int r = 0;
	    	int sum = 0;
	    	// 统计其中零的个数
	    	int zeros = 0;
	    	while(r<nums.length) {
	    		 if(nums[r]==0) {
	    			zeros++;
	    		 }
	    		 r++;
	    		 // 加到一定数量
	    		 while(zeros>1) {
	    			 if(nums[l]==0) {
	    				 zeros--;
	    			 }
	    			 l++;
	    		 }
	    		 sum = Math.max(sum, r-l+1);
	    	}
	    	return sum;
	    }
	}
}
