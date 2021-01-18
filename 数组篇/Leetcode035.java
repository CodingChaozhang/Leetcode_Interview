public class Leetcode035 {
	class Solution {
	    public int searchInsert(int[] nums, int target) {
	    	return binarySearch(nums, 0, nums.length-1, target);
	    }
	    public int binarySearch(int[] nums,int left,int right,int target) {
	    	// 求左边
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]<target) {
	    			left = mid + 1;
	    		}else if(nums[mid]>=target) {
	    			right = mid-1;
	    		}
	    	}
	    	return left;
	    }
	}
}
