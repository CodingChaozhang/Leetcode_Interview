// 丢失的数字
import java.util.*;
public class Leetcode268 {
	class Solution {
	    // 解题思路1 有序+二分查找
		public int missingNumber(int[] nums) {
			Arrays.sort(nums);
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
		// HashMap解题
		public int missingNumber_2(int[] nums) {
			HashSet<Integer> hashset = new HashSet<>();
			for(int num:nums) {
				hashset.add(num);
			}
			// 对整个进行遍历
			for(int i=0;i<=nums.length;i++) {
				if(!hashset.contains(i)) {
					return i;
				}
			}
			return -1;
		}

	}
}
