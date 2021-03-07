
import java.util.HashMap;

// 和为K的子数组
public class Leetcode560 {
	class Solution {
	    public int subarraySum(int[] nums, int k) {
	    	// 暴力解法
	    	int res  = 0 ;
	    	// 开始
	    	for(int start=0;start<nums.length;start++) {
	    		int temp = 0;
	    		for(int end=start;end>=0;end--) {
	    			temp += nums[end];
	    			if(temp==k) {
	    				res += 1;
	    			}
	    		}
	    	}    	
	    	
	    	return res;
	    }
	    
	    // 
	    public int subarraySum_2(int[] nums, int k) {
	    	// 前缀和+hashamp
	    	HashMap<Integer,Integer> hashMap = new HashMap<>();
	    	int res = 0;
	    	int pre = 0;
	    	hashMap.put(0,1);
	    	// 开始
	    	for(int i=0;i<nums.length;i++) {
	    		pre += nums[i];
	    		if(hashMap.containsKey(pre-k)) {
	    			res += hashMap.get(pre-k);
	    		}
	    		
	    		hashMap.put(pre,hashMap.getOrDefault(pre,0)+1);
	    	}
	    	return res;
	    }
	}
}
