// 0-n-1中缺失的数字
public class Leetcode_Offer53 {
	class Solution {
	    public int missingNumber(int[] nums) {
	    	// 暴力解法
	    	for(int i=0;i<nums.length;i++) {
	    		if(nums[i]!=i) {
	    			return i;
	    		}
	    	}
	    	return nums.length;
	    }
	    // 是否可以缩短呢？通过二分查找来
	    public int missingNumber_2(int[] nums) {
	    	int left = 0;
	    	int right = nums.length-1;
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]==mid) {
	    			left = mid+1;
	    		}else {
	    			right = mid-1;
	    		}
	    	}
	    	return left;
	    }
	    

	}
}
