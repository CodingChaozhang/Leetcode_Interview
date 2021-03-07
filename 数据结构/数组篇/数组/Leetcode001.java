// HashMap解题的思路
import java.util.*;
public class Leetcode001 {
	class Solution {
	    public int[] twoSum(int[] nums, int target) {
	    	HashMap<Integer,Integer> hashMap = new HashMap<>();
	    	int index_left=0,index_right = 0;
	    	for(int i=0;i<nums.length;i++) {
	    		// 如果有其值
	    		if(hashMap.containsKey(target-nums[i])) {
	    			index_left  = hashMap.get(target-nums[i]);
	    			index_right = i;
	    			break;
	    		}
	    		hashMap.put(nums[i], i);
	    	}
	    	return new int[] {index_left,index_right};
	    }
	}
}
