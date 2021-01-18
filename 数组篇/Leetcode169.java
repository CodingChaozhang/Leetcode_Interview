/**
 * 多数元素
 * @author LvChaoZhang
 *
 */
import java.util.*;

public class Leetcode169 {
	class Solution {
	    public int majorityElement(int[] nums) {
	    	int res = nums[0];
	    	int count = 1;
	    	for(int i=1;i<nums.length;i++) {
	    		if(count==0) {
	    			res = nums[i];
	    		}
	    		count += nums[i]==res?1:-1;
	    	}
	    	return res;
	    }
		
		public int majorityElement(int[] nums) {
	    	// 用hashmap来解题
	    	HashMap<Integer,Integer> hashMap = new HashMap<>();
	    	for(int i=0;i<nums.length;i++) {
	    		if(hashMap.containsKey(nums[i])) {
	    			// hashMap包含其值 更新频率
	    			hashMap.put(nums[i],hashMap.get(nums[i])+1);
	    		}else {
	    			// hashMap不含其值
	    			hashMap.put(nums[i],1);
	    		}
	    	}	
	    	int length = nums.length;
	    	int frequency = length >> 1;
	    	// 结果存放
	    	Map.Entry<Integer, Integer> res = null;
	    	// 对hashMap进行遍历
	    	for(Map.Entry<Integer, Integer> entry:hashMap.entrySet()) {
	    		if(res==null || entry.getValue()>res.getValue()) {
	    			res = entry;
	    		}
	    	}
	    	return res.getKey();
	    	
	    }
		
		public int majorityElement(int[] nums) {
            Arrays.sort(nums);
	    	return nums[nums.length/2];
		}
		
	}
}
