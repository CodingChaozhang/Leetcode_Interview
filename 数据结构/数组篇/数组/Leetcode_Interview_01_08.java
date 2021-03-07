// 零矩阵
public class Leetcode01_08 {
	class Solution {
	    public void setZeroes(int[][] matrix) {
	    	// 开辟两个行 列数组来保存是否出现0了
	    	int rows = matrix.length;
	    	int cols = matrix[0].length;
	    	boolean[] row_arr = new boolean[rows];
	    	boolean[] row_col = new boolean[cols];
	    	// 对其遍历
	    	for(int i=0;i<rows;i++) {
	    		for(int j=0;j<cols;j++) {
	    			if(matrix[i][j]==0) {
	    				row_arr[i] = true;
	    				row_col[j] = true;
	    			}
	    		}
	    	}
	    	// 渲染
	    	for(int i=0;i<rows;i++) {
	    		for(int j=0;j<cols;j++) {
	    			if(row_arr[i]||row_col[j]) {
	    				matrix[i][j] = 0;
	    			}
	    		}
	    	}
	    	
	    }
	    
	}
}
