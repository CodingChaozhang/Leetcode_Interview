// 最长连续递增序列
public class Leetcode674 {
	class Solution {
	    public int findLengthOfLCIS(int[] nums) {
	    	if(nums.length==0) {
	    		return 0;
	    	}
	    	int res = 1;
	    	int res_temp  = 1;
	    	for(int i=1;i<nums.length;i++) {
	    		// 判断
	    		if(nums[i]>nums[i-1]) {
	    			res_temp++;
	    		}else {
	    			res_temp = 1;
	    		}
	    		res = Math.max(res, res_temp);
	    	}
	    	
	    	return res;
	    }
	}
}
