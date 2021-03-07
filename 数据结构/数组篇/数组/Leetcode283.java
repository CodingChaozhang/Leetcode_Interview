public class Leetcode283 {
	class Solution {
	    public void moveZeroes(int[] nums) {
	    	// 快慢指针
	    	int left = -1;
	    	int index = 0;
	    	while(index<nums.length) {
	    		if(nums[index]==0) {
	    			// 相等怎么办
	    			index++;
	    		}else {
	    			// 不相等怎么办
	    			swap(nums,++left,index++);
	    		}
	    	}
	    }
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	}
}
