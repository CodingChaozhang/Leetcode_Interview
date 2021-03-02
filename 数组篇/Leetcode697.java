
import java.util.*;
public class Leetcode697 {
	class Solution {
		// 数组的度
	    public int findShortestSubArray(int[] nums) {
	    	// 用hashmap求解 key为该值 value为次数，开始 结束位置
	    	HashMap<Integer,int[]> hashMap = new HashMap<>();
	    	// 遍历
	    	for(int i=0;i<nums.length;i++) {
	    		// 不是第一次放入
	    		if(hashMap.containsKey(nums[i])) {
	    			// 更新值
	    			hashMap.get(nums[i])[0]++;
	    			// 更新最后一个区间的值
	    			hashMap.get(nums[i])[2]=i;
	    			
	    			
	    		}else {
	    			// 第一次放入
	    			hashMap.put(nums[i], new int[] {1,i,i});
	    		}	
	    	}
	    	// 度
	    	int res = 0;
	    	// 长度
	    	int minLen = 0;
	    	// 对其hahsmap遍历
	    	for(Map.Entry<Integer, int[]> map:hashMap.entrySet()) {
	    		int[] arr = map.getValue();
	    		if(res<arr[0]) {
	    			// 初始化
	    			res = arr[0];
	    			minLen = arr[2]-arr[1]+1;
	    		}else if(res==arr[0]) {
	    			//更新度的最小长度
	    			minLen = Math.min(minLen, arr[2]-arr[1]+1);
	    		}
	    	}
	    	return minLen;
	    	
	    }
	}
}
