// 好数对的数目
import java.util.*;
public class Leetcode1512 {
	class Solution {
	    public int numIdenticalPairs(int[] nums) {
	    	// 结果存储
	    	int res = 0;
	    	// HashMap的辅助结构来解题
	    	HashMap<Integer,Integer> hashMap = new HashMap<>();
	    	// 对其遍历
	    	for(int i=0;i<nums.length;i++) {
	    		//如果已存在
	    		if(hashMap.containsKey(nums[i])) {
	    			// 取出
	    			res += hashMap.get(nums[i]);
	    			// 更新
	    			hashMap.put(nums[i],hashMap.get(nums[i])+1);
	    		}else {
	    			// 之前未存在
	    			hashMap.put(nums[i],1);
	    		}
	    	}
	    	return res;
	    }
	}
}
