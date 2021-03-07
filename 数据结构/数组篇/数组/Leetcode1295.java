import java.util.*;
public class Leetcode1295 {
	class Solution {
	    public int findNumbers(int[] nums) {
	    	int res = 0;
	    	// 该数的位数
	    	int count = 0;
	    	// 该数的余数
	    	int m = 0;
	    	
	    	// 遍历
	    	for(int num:nums) {
	    		count = 0;
	    		// 遍历
	    		while(num>0) {
	    			m = num%10;
	    			count++;
	    			num = num/10;
	    		}
	    		if(count%2==0) {
	    			res++;
	    		}
	    	}
	    	return res;
	    }	
	    
	    public int findNumbers_2(int[] nums) {
	    	int res = 0;
	    	for(int num:nums) {
	    		// 判断
	    		if((int)(Math.log10(num)+1)%2==0) {
	    			res++;
	    		}
	    	}
	    	return res;
	    }

	}
}
