public class Leetcode034 {
	class Solution {
	    public int[] searchRange(int[] nums, int target) {
	    	int left = binaryLeft(nums, 0, nums.length-1, target);
	    	int right = binrayRight(nums, 0, nums.length-1, target);
	    	return new int[] {left,right};
	    }
	    
	    // 二分查找其左边界
	    public int binaryLeft(int[] nums,int left,int right,int target) {
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]>=target) {
	    			right = mid-1;
	    		}else if(nums[mid]<target) {
	    			left = mid+1;
	    		}
	    		
	    	}
            
            // 判断是否越界
            if(left>=nums.length||nums[left]!=target){
                return -1;
            }
	    	return left;
	    }
	    
	    // 二分查找其右边界
	    public int binrayRight(int[] nums,int left,int right,int target) {
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]>target) {
	    			right = mid - 1;
	    		}else if(nums[mid]<=target) {
	    			left = mid + 1;
	    		}
	    	}
            // 判断是否越界
            if(right<0 || nums[right]!=target){
                return -1;
            } 
	    	return right;
	    }
	}
}
