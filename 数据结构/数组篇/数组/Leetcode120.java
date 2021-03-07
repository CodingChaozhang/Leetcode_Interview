package com.lcz.leetcode;
import java.util.*;
// 三角形最小路径和
// 解题思路 动态规划
public class Leetcode120 {
	class Solution {
	    public int minimumTotal(List<List<Integer>> triangle) {
	    	// 动态规划 定义 初始化 转移方程
	    	int size = triangle.size();
	    	// 定义
	    	int[][] dp = new int[size][size];
	    	// 初始化
	    	dp[0][0] = triangle.get(0).get(0);
	    	// 转移方程
	    	for(int i=1;i<size;i++) {
	    		List<Integer> temp = triangle.get(i);
	    		// 开始接着遍历 每一行
	    		int j=0;
	    		if(j==0) {
    				dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
    			}
	    		for(j=1;j<temp.size()-1;j++) {
	    			dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);    			
	    		}
	    		if(j==temp.size()-1) {
	    			dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
	    		}
	    	}
	    	// 从最后一行找最小值
	    	int minValue = dp[size-1][0];
	    	for(int i=1;i<size;i++) {
	    		minValue = Math.min(minValue, dp[size-1][i]);
	    	}
	    	return minValue;
	    }
	}
}
