
public class Leetcode026 {
	class Solution {
	    public int removeDuplicates(int[] nums) {
	            // 双指针问题  快慢指针 快指针遍历原数组 慢指针维护新数组的长度
	    		if(nums==null || nums.length<1) {
	    			return 0;
	    		}
	    		// left维护新数组
		    	int left  = 0;
		    	int index = 1;
		    	while(index<nums.length) {
		    		if(nums[index]==nums[left]) {
		    			// 不等
		    			index++;
		    		}else {
		    			// 相等
		    			nums[++left] = nums[index++];
		    		}
		    	}
		    	return left+1;
	    }
	    
	}
}	
