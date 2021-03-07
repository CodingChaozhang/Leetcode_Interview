package com.lcz.leetcode;
// 找到所有数组中消失的数字
import java.util.*;
public class Leetcode0448 {
	class Solution {
		// 思路一、原地修改数组
	    public List<Integer> findDisappearedNumbers(int[] nums) {
	    	for(int i=0;i<nums.length;i++) {
	    		// 3放置在索引位置2上
	    		while(nums[i]!=nums[nums[i]-1]) {
	    			swap(nums,i,nums[i]-1);
	    		}
	    	}
	    	// 在遍历一次
	    	// 存储结果
	    	List<Integer> res = new ArrayList<>();
	    	for(int i=0;i<nums.length;i++) {
	    		if(nums[i]!=i+1) {
	    			res.add(i+1);
	    		}
	    	}
	    	return res;
	    }
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	    
	    

	}
}
