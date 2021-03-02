// 转置矩阵
public class Leetcode867 {
	class Solution {
	    public int[][] transpose(int[][] matrix) {
	    	int row = matrix.length;
	    	int col = matrix[0].length;
	    	// 开始新的空间
	    	int[][] new_matrix = new int[col][row];
	    	// 开始遍历
	    	for(int i=0;i<row;i++) {
	    		for(int j=0;j<col;j++) {
	    			new_matrix[j][i] = matrix[i][j];	    			
	    		}
	    	}
	    	return new_matrix;
	    }
	}
}
