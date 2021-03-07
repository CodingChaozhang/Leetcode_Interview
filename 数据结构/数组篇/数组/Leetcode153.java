package com.lcz.leetcode;
// 寻找旋转排序数组中的最小值
public class Leetcode153 {
	class Solution {
	    public int findMin(int[] nums) {
	    	// 二分查找
	    	int left = 0;
	    	int right = nums.length-1;
	    	// 剪枝
	    	
	    	while(left<=right) {
	    		// 单调递增时直接返回
	    		if(nums[left]<=nums[right]) {
		    		return nums[left];
		    	}
	    		
	    		// 判断哪边有序
	    		int mid = left + ((right-left)>>1);
	    		if(nums[left]<=nums[mid]) {
	    			// 从left到mid之间有序
	    			left = mid+1;
	    		}else if(nums[left]>nums[mid]) {
	    			// 无序
	    			right = mid;
	    		}
	    	}
	    	return -1;
	    }
	}
}
