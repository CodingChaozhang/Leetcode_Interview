// 托普利茨矩阵
public class Leetcode766 {
	class Solution {
		// 矩阵中每一条从左上到右下的对角线上的元素都相同
	    public boolean isToeplitzMatrix(int[][] matrix) {
	    	// 遍历除了第一行和第一列的元素，让其与左上角的元素对比即可
	    	boolean flag = true;
	    	for(int i=1;i<matrix.length;i++) {
	    		for(int j=1;j<matrix[0].length;j++) {
	    			if(matrix[i][j]!=matrix[i-1][j-1]) {
	    				return false;
	    			}
	    		}
	    	}
	    	return flag;
	    }
	    
	}
}
