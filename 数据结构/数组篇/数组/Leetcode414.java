package com.lcz.leetcode;
// 第三大的数
import java.util.*;
public class Leetcode414 {
	class Solution {
		// 直接逻辑
	    public int thirdMax(int[] nums) {
	    	long firstMax = Long.MIN_VALUE;
	    	long secondMax = Long.MIN_VALUE;
	    	long thirdMax = Long.MIN_VALUE;
	    	// 遍历
	    	for(int num:nums) {
	    		if(num>firstMax) {
	    			thirdMax = secondMax;
	    			secondMax = firstMax;
	    			firstMax = num;
	    		}else if(num>secondMax&&firstMax!=num) {
	    			thirdMax = secondMax;
	    			secondMax = num;
	    		}else if(num>thirdMax&&secondMax!=num&&firstMax!=num) {
	    			thirdMax = num;
	    		}
	    	}
	    	return thirdMax==Long.MIN_VALUE?(int)firstMax:(int)thirdMax;
	    }
	    
	    // 排序+HashSet去重
	    public int thirdMax_2(int[] nums) {
	    	
	    	HashSet<Integer> hashset = new HashSet<>();

	    	// 结果
	    	int count = 3;
	    	// 排序
	    	Arrays.sort(nums);
	    	// 对其遍历
	    	for(int i=nums.length-1;i>=0;i--) {
	    		if(hashset.contains(nums[i])) {
	    			continue;
	    		}else {
	    			hashset.add(nums[i]);
	    			count--;
	    		}
	    		if(count==0) {
	    			return nums[i];
	    		}
	    	}
	    	return nums[nums.length-1];
	    	
	    }

	}
}
