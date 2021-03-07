// 非递减数列
public class Leetcode665 {
	class Solution {
	    // 判断存在几个谷底 两个数大，有两种选择，前一个变小 或者后一个变大，具体选择看前一个的前一个
		public boolean checkPossibility(int[] nums) {
	    	int count = 0;
	    	if(nums.length<3) {
	    		return true;
	    	}
	    	// 最开始两个只有1个选择
	    	if(nums[0]>nums[1]) {
	    		nums[0] = nums[1];
	    		count++;
	    	}
	    	
	    	// 后面的
	    	for(int i=1;i<nums.length-1;i++) {
	    		int right = nums[i+1];
	    		if(nums[i]>right) {
	    			count++;
	    			if(count>1) {
	    				return false;
	    			}
	    			int left =  nums[i-1];
	    			if(left>right) {
	    				nums[i+1]=nums[i];
	    			}else {
	    				nums[i] = left;
	    			}
	    		}
	    		
	    	}
	    	return true;
	    }
	}
}
