// 摆动序列 
public class Leetcode376 {
	class Solution {
		// 不要求连续
	    public int wiggleMaxLength(int[] nums) {
	    	if(nums.length<1) {
	    		return 0;
	    	}
	    	int up =1 ;
	    	int down = 1;
	    	int res = 1;
	    	for(int i=1;i<nums.length;i++) {
	    		if(nums[i]>nums[i-1]) {
	    			up = down+1;
	    		}else if(nums[i]<nums[i-1]) {
	    			down = up+1;
	    		}
	    		res = Math.max(up, down);
	    	}
	    	return res;
	    }
	}
}
