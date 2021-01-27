// 最长连续序列
import java.util.*;
public class Leetcode128 {
	class Solution {
	    public int longestConsecutive(int[] nums) {
	    	// 就是连续的
	    	HashSet<Integer> hashset = new HashSet<Integer>();
	    	for(int num:nums) {
	    		hashset.add(num);
	    	}
	    	// 结果
	    	int res = 0;
	    	//对hashset遍历
	    	for(int num:hashset) {
	    		// 如果其不存在前缀
	    		if(!hashset.contains(num-1)) {
	    			// 直接找其后缀
	    			int curNum = num;
	    			int curLength = 1;
	    			while(hashset.contains(curNum+1)) {
	    				curNum += 1;
	    				curLength += 1;
	    			}
	    			res = Math.max(res, curLength);
	    		}
	    	}
	    	return res;
	    }
	}
}
