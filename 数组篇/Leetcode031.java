// 下一个排列
public  class Leetcode031 {
	class Solution {
	    public void nextPermutation(int[] nums) {
	    	// 解题思路 两边扫描
	    	// 先找第一个较小的数 从右边开始
	    	int i = nums.length-2;
	    	while(i>=0&&nums[i]>=nums[i+1]) {
	    		i--;
	    	}
	    	if(i>=0) {
	    		// 找第二个数 较大的数
	    		int j = nums.length-1;
	    		while(j>=0&&nums[j]<=nums[i]) {
	    			j--;
	    		}
	    		// 交换两个数
	    		swap(nums,i,j);
	    	}
	    	// 链表交换
	    	reverse(nums,i+1);
	    }
	    
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	    // 链表反转
	    public void reverse(int[] nums,int start) {
	    	int left = start;
	    	int right  = nums.length-1;
	    	while(left<=right) {
	    		swap(nums,left++,right--);
	    	}
	    }
	}
}
