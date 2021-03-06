package com.lcz.leetcode;
// 最小差
import java.util.*;
public class Leetcode_Interview_16_06 {
	class Solution {
	    public int smallestDifference(int[] a, int[] b) {
	    	// 解题思路：排序 + 双指针
	    	Arrays.sort(a);
	    	Arrays.sort(b);
	    	// 最小值
	    	int minValue = Integer.MAX_VALUE;
	    	int left = 0;
	    	int right = 0;
	    	while(left<a.length&&right<b.length) {
	    		int dif = a[left]-b[right];
	    		minValue = Math.min(Math.abs(dif), minValue);
	    		// 判断
	    		if(dif<0) {
	    			left++;
	    		}else {
	    			right++;
	    		}
	    	}
	    	return minValue;
	    }
	}
}
