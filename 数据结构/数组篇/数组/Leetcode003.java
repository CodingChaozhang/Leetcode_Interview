// 无重复字符的最长子串
import java.util.*;
public class Leetcode003 {
	class Solution {
		// 解题思路：滑动窗口 需要判断其是否已放入
		// 用辅助结构：双向队列
	    public int lengthOfLongestSubstring(String s) {
	    	char[] arr = s.toCharArray();
	    	// 对其用hashMap
	    	HashMap<Character,Integer> hashMap = new HashMap<>();
	    	// 滑动窗口的解法
	    	int l = 0;
	    	int r = 0;
	    	int sum = 0;
	    	while(r<arr.length) {
	    		// 满足条件则扩大
	    		char c = arr[r];

	    		if(hashMap.containsKey(c)) {
	    			l = Math.max(l, hashMap.get(c)+1);
	    		}
	    		hashMap.put(c, r);
	    		sum = Math.max(sum, r-l+1);
	    		r++;
	    	}
	    	return sum;
	    }
	}
}
