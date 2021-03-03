import java.util.*;
public class Leetcode0209 {
	class Solution {
	    public int minSubArrayLen(int target, int[] nums) {
	    	// 解题思路：滑动窗口的解法
	    	int l = 0;
	    	int r = 0;
	    	// 其中的一个临时数组和
	    	int temp = 0;
	    	// 结果
	    	int res = nums.length+1;
	    	while(r<nums.length) {
	    		temp += nums[r];
	    		
	    		while(temp>=target) {
	    			temp-=nums[l];
	    			res = Math.min(res, r-l+1);
	    			l++;
	    			
	    		}
	    		// 扩大
	    		r++;
	    	}
	    	return res==nums.length+1?0:res;
	    }
	}
}
