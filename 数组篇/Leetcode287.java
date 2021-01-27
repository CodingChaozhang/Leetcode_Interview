// 寻找重复数
import java.util.*;
public class Leetcode287 {
	class Solution {
		// 第一种解题思路：排序
	    public int findDuplicate(int[] nums) {
	       Arrays.sort(nums);
	       for(int i=0;i<nums.length-1;i++) {
	    	   if(nums[i]==nums[i+1]) {
	    		   return nums[i];
	    	   }
	       }
	       return 0;
	    }
	    
	    // 第二种解题思路：快慢指针的解题 思路 将其看成一个循环的链表即可
	    public int findDuplicate_2(int[] nums) {
	    	// 必定存在重复值， 必定相遇
	    	int low = 0;
	    	int fast = 0;
	    	while(true) {
	    		// 走两步
	    		low = nums[low];
	    		fast = nums[nums[fast]];
	    		// 相遇； 
	    		if(low==fast) {
	    			// 开始找起点 走一步
	    			fast = 0;
	    			while(nums[fast]!=nums[low]) {
	    				fast = nums[fast];
	    				low = nums[low];
	    			}
	    			return nums[low];
	    		}
	    	}
	    }

	}
}
