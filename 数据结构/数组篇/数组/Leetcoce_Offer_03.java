
import java.util.*;
public class Leetcoce_Offer_03 {
	class Solution {
		// 辅助数据结构来解题
	    public int findRepeatNumber(int[] nums) {
	    	HashSet<Integer> hashset = new HashSet<>();
	    	for(int i=0;i<nums.length;i++) {
	    		//判断
	    		if(hashset.contains(nums[i])) {
	    			return nums[i];
	    		}else {
	    			hashset.add(nums[i]);
	    		}
	    	}
	    	return -1;
	    }
	    
	    // 在原数组上进行操作 因为原数组范围固定在0-n-1中了 。
	    public int findRepeatNumber_2(int[] nums) {
	    	// 每个位置都在i上即可
	    	int temp;
	    	for(int i=0;i<nums.length;i++) {
	    		while(i!=nums[i]) {
	    			if(nums[i]==nums[nums[i]]) {
	    				// 如果有一个值跟已排序好的值相等
	    				return nums[i];
	    			}
	    			// 交换两值，使其回到原位值
	    			temp = nums[i];
	    			nums[i] = nums[temp];
	    			nums[temp] = temp;
	    		}
	    	}
	    	return -1;
	    }
	}
}
