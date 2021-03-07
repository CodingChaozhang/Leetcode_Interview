import java.util.*;
public class Leetcode1208 {
	class Solution {
		// 尽可能使字符串相等 // 解题思路：滑动窗口
	    public int equalSubstring(String s, String t, int maxCost) {
	    	char[] s_arr = s.toCharArray();
	    	char[] t_arr = t.toCharArray();
	    	// 代价数组
	    	int len = s.length();
	    	int[] cost_arr = new int[len];
	    	// 初始化
	    	for(int i=0;i<len;i++) {
	    		cost_arr[i] = Math.abs(s_arr[i]-t_arr[i]);
	    	}
	    	// 对其遍历
	    	int start = 0;
	    	int end   = 0;
	    	int useCost = 0;
	    	// 结果
	    	int res = 0;
	    	while(end<len) {
	    		useCost += cost_arr[end];
	    		while(useCost>maxCost) {
	    			useCost -= cost_arr[start];
	    			start++;
	    		}
	    		res = Math.max(res,end-start+1);
	    		end++;
	    	}
	    	return res;
	    }
	}
}
