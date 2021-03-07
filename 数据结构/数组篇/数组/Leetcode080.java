// 删除排序数组中的重复项II
public class Leetcode080 {
	class Solution {
		// 原地修改数组 解题思路：一个维护新的 一个遍历旧的
	    public int removeDuplicates(int[] nums) {
	    	int low = 0;
	    	int fast = 1;
	    	int fre = 0;
	    	while(fast<nums.length) {
	    		// 老数组
	    		if(nums[fast]==nums[low]) {
	    			fre++;
	    		}else {
	    			fre = 0;
	    		}
	    		
	    		// 新数组
	    		if(fre<2) {
	    			low++;
	    			nums[low] = nums[fast];
	    		}
	    		
	    		// 接着走
	    		fast++;
	    	}
	    	return low+1;
	    }
	}
}
