// 存在重复元素
import java.util.*;
public class Leetcode217 {
	class Solution {
	    public boolean containsDuplicate(int[] nums) {
	    	// 第一种对其用HashSet辅助结构来解题
	    	HashSet<Integer> hashSet = new HashSet<Integer>();
	    	for(int num:nums) {
	    		if(!hashSet.contains(num)) {
	    			hashSet.add(num);
	    		}else {
	    			return true;
	    		}
	    	}
	    	return false;
 	    }
	    
	}
}
