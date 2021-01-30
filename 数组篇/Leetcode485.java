// 最大连续1的个数
public class Leetcode485 {
	class Solution {
	    public int findMaxConsecutiveOnes(int[] nums) {
	    	int res = 0;
	    	int res_temp = 0;
	    	for(int i=0;i<nums.length;i++) {
	    		if(nums[i]==1) {
	    			res_temp++;
	    			res = Math.max(res_temp, res);
	    		}else if(nums[i]==0) {
	    			res_temp = 0;
	    			
	    		}
	    	}
	    	return res;
	    }
	}
}
