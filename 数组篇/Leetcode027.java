public class Leetcode027 {
	class Solution {
	    public int removeElement(int[] nums, int val) {
	    	// 存储长度
	    	int left = -1;
	    	int index = 0;
	    	while(index<nums.length) {
	    		if(nums[index]==val) {
	    			// 等于查看下一个
	    			index++;
	    		}else {
	    			// 不等于交换
	    			swap(nums,++left,index++);
	    		}
	    	}
	    	return left+1;
	    }
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	}
}
