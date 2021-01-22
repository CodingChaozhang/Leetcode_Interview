// 剑指Offer29 顺时针打印矩阵
import java.util.*;
public class Leetcode_Offer_29 {
	class Solution {
	    public int[] spiralOrder(int[][] matrix) {
	    	if(matrix.length==0||matrix[0].length==0){
	             return new int[]{};
	         }
	         int start_row = 0;
		        int start_col = 0;
		        int end_row   = matrix.length-1;
		        int end_col   = matrix[0].length-1;
		        int[] res       = new int[matrix.length*matrix[0].length];
		        int index	  = 0;
		        while(start_row<=end_row&&start_col<=end_col) {
		        	// 开始遍历
		        	for(int i=start_col;i<=end_col;i++) {
		        		res[index++] = matrix[start_row][i];
		        	}
		        	for(int i=start_row+1;i<=end_row;i++) {
		        		res[index++] = matrix[i][end_col];
		        	}
		        	// 判断是否为单列
		        	if(start_row!=end_row) {
		        		for(int i=end_col-1;i>=start_col;i--) {
		        			res[index++] = matrix[end_row][i];
		        		}
		        	}
		        	// 判断是否为单行
		        	if(start_col!=end_col) {
		        		for(int i=end_row-1;i>start_row;i--) {
		        			res[index++] = matrix[i][start_col];
		        		}
		        	}
		        	start_row++;
		        	start_col++;
		        	end_row--;
		        	end_col--;
		        }
		        return res;
	    }
	}
}
