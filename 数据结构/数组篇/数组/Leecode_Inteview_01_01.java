import java.util.*;
// 解题思路 用HashMap或者HashSet
public class Leetcode_Interview_01_01 {
	class Solution {
	    public boolean isUnique(String astr) {
	    	char[] dict = astr.toCharArray();
	    	// 辅助结构
	    	HashSet<Character> hashSet = new HashSet<>();
	    	for(int i=0;i<dict.length;i++) {
	    		if(!hashSet.isEmpty() && hashSet.contains(dict[i])) {
	    			return false;
	    		}else {
	    			hashSet.add(dict[i]);
	    		}
	    	}
	    	return true;
	    }
	}
}
