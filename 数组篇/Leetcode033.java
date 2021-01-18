// 搜索旋转排序数组
public class Leetcode033 {
	class Solution {
	    public int search(int[] nums, int target) {
	    	// 可用二分查找的办法
	    	return binarySearch(nums, 0, nums.length-1, target);
	    }
	    public int binarySearch(int[] nums,int left,int right,int target) {
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]==target) {
	    			return mid;
	    		}
	    		// 判断mid哪个区间是有序的
	    		if(nums[mid]>=nums[left]) {
	    			// 从left到mid之间是有序的
	    			// 再接着判断target在哪里
	    			if(target>=nums[left]&&target<nums[mid]) {
	    				right = mid-1;
	    			}else {
	    				left =  mid+1;
	    			}
	    		}else {
	    			// 从mid到right之间是有序的
	    			if(target>nums[mid]&&target<=nums[right]) {
	    				left =  mid + 1;
	    			}else {
	    				right = mid - 1;
	    			}
	    		}
	    	}
	    	
	    	return -1;
	    }
	}
}
