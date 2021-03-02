// 爱生气的书店老板
public class Leetcode1052 {
	class Solution {
	    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
	    	// 解题思路：找到所有不生气的， 再计算一次滑动窗口生气中的最大值
	    	
	    	int res = 0;
	    	for(int i=0;i<customers.length;i++) {
	    		if(grumpy[i]==0) {
	    			res += customers[i];
	    			// 顺便使对应顾客数为0
	    			customers[i] = 0;
	    		}
	    	}
	    	
	    	// 滑动窗口
	    	int extra = 0;
	    	int temp  = 0;
	    	int l = 0;
	    	int r = 0;
	    	// 目前只有生气的有顾客
	    	while(r<customers.length) {
	    		temp += customers[r];
	    		// 判断滑动窗口多了
	    		if(r-l+1>X) {
	    			temp -= customers[l];
	    			l++;
	    		}
	    		//记录此时的值
	    		extra = Math.max(extra, temp);
	    		r++;
	    	}
	    	return res+extra;
	    }
	}
}
