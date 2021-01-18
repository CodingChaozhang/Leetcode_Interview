package com.lcz.leetcode;

public class Leetcode048 {
	class Solution {
	    public void rotate(int[][] matrix) {
	    	// 左上角和右下角
	    	int row_left = 0;
	    	int col_left = 0;
	    	int row_right = matrix.length-1;
	    	int col_right = matrix[0].length-1;
	    	while(row_left<row_right) {
	    		swap(matrix,row_left++,col_left++,row_right--,col_right--);
	    	}
	    }
	    
	    // 交换
	    public void swap(int[][] matrix,int row_left,int col_left,int row_right,int col_right) {
	    	int temp;
	    	int times = row_right-row_left;
	    	for(int i=0;i<times;i++) {
	    		temp 						   = matrix[row_left][col_left+i];
	    		matrix[row_left][col_left+i]   = matrix[row_right-i][col_left];
	    		matrix[row_right-i][col_left]  = matrix[row_right][col_right-i];
	    		matrix[row_right][col_right-i] = matrix[row_left+i][col_right];
	    		matrix[row_left+i][col_right]   = temp;
	    	}
	    }
	}
}
