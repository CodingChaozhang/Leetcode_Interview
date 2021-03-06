// 连续数列
public class Leetcode_Interview_16_17 {
	class Solution {
	    public int maxSubArray(int[] nums) {
	    	// 解题思路：用滑动窗口
	    	int left = 0;
	    	int right = 1;
	    	// 临时值
	    	int temp_sum = nums[0];
	    	int res = nums[0];
	    	int len = nums.length;
	    	while(right<len) {
	    		temp_sum += nums[right];
	    		// 左窗口移动时机
	    		while(nums[right]>temp_sum) {
	    			temp_sum -= nums[left];
	    			left++;
	    		}
	    		res = Math.max(res, temp_sum);
	    		right++;
	    		
	    	}
	    	return res;
	    }
	}
}
