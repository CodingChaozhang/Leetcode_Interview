// 乘积最大连续子数组
public class Leetcode152 {
	class Solution {
	    public int maxProduct(int[] nums) {
	        // 维护一个最大的子数组，维护一个最小的子数组
	    	int res = Integer.MIN_VALUE;
	    	int imax = 1;
	    	int imin = 1;
	    	for(int i=0;i<nums.length;i++) {
	    		// 负数的时候交换
	    		if(nums[i]<0) {
	    			int temp = imax;
	    			imax = imin;
	    			imin = temp;
	    		}
	    		// 看成全是正数
	    		imax = Math.max(imax*nums[i], nums[i]);
	    		imin = Math.min(imin*nums[i], nums[i]);
	    		res = Math.max(imax,res);
	    	}
	    	return res;
	    }
	}
}
