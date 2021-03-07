// 存在重复元素II
import java.util.*;
public class Leetcode219 {
	class Solution {
	    public boolean containsNearbyDuplicate(int[] nums, int k) {
	    	// 用HashMap来存储
	    	HashMap<Integer,Integer> hashMap = new HashMap<>(); 
	    	// 直接找最小的索引
	    	int res = nums.length;
	    	// 对其遍历
	    	for(int i=0;i<nums.length;i++) {
	    		// 如果没有该值 
	    		if(!hashMap.containsKey(nums[i])) {
	    			hashMap.put(nums[i], i);
	    		}else{
	    			// 如果存在了当前值 先更新当前的索引值
	    			int lastIndex = hashMap.get(nums[i]);
	    			int curIndex = i;
	    			res = Math.min(res, curIndex-lastIndex);
	    			// 并更新hashMap
	    			hashMap.put(nums[i],curIndex);
	    		}
	    	}
	    	// 最后查看
	    	return res==nums.length?false:res>k?false:true;
	    }
	}
}
