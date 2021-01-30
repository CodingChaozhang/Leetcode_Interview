// 除自身以外数组的乘积
public class Leetcode238 {
	class Solution {
		// 不能用除法 先计算左边 再计算右边
	    public int[] productExceptSelf(int[] nums) {
	    	int[] res = new int[nums.length];
	    	int left = 1;
	    	int right = 1;
	    	// 先计算左边
	    	for(int i=0;i<nums.length;i++) {
	    		res[i] = left;
	    		left *= nums[i];
	    	}
	    	// 再计算右边
	    	for(int i=nums.length-1;i>=0;i--) {
	    		res[i] *= right;
	    		right *= nums[i];
	    	}
	    	return res;
	    }
	}
}
