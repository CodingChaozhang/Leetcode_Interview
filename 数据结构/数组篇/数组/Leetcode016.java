// 最接近的三数之和
import java.util.*;
public class Leetcode016 {
	class Solution {
	    public int threeSumClosest(int[] nums, int target) {
	            // 排序+双指针即可
		    	Arrays.sort(nums);
	            // 初始化的方式
		    	int res  = nums[0]+nums[1]+nums[2];
		    	for(int i=0;i<nums.length-2;i++) {
		    		int left = i+1;
		    		int right = nums.length-1;
		    		// while循环
		    		while(left<right) {
		    			// 当前值
			    		int temp_target = nums[i] + nums[left] + nums[right];
	                    res = (Math.abs(target-res))>(Math.abs(target-temp_target))?temp_target:res;
			    		if(temp_target==target) {
			    			return temp_target;
			    		}else if(temp_target<target) {
			    			left++;
			    		}else {
			    			right--;
			    		}
		    		}
		    	}
		    	return res;
	    }
	}
}
