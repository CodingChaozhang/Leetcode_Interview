// 搜索二维矩阵
public class Leetcode074 {
	class Solution {
		// 解题思路 因二维矩阵特殊，所以从右上角出发
	    public boolean searchMatrix(int[][] matrix, int target) {
	    	int m = matrix.length;
	    	int n = matrix[0].length-1;
	    	int start_x = 0;
	    	int start_y = n;
	    	// 开始搜索
	    	while(start_x<m&&start_y>=0) {
	    		if(matrix[start_x][start_y]==target) {
	    			return true;
	    		}else if(matrix[start_x][start_y]>target) {
	    			start_y--;
	    		}else if(matrix[start_x][start_y]<target) {
	    			start_x++;
	    		}
	    	}
	    	return false;
	    }
	}
}
