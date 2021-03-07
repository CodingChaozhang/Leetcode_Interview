// 二维数组中的查找
public class Leetcode_Offer_04 {
	class Solution {
	    public boolean findNumberIn2DArray(int[][] matrix, int target) {
	    	// 逻辑题目
	    	// 重点就是从左上角出发
	    	if(matrix==null || matrix.length==0 || matrix[0].length==0) {
	    		return false;
	    	}
	    	int n= matrix.length;
	    	int m = matrix[0].length;
	    	// 左上角点坐标为cur_x,cur_y;
	    	int cur_x = 0;
	    	int cur_y = m-1;
	    	// 满足条件
	    	while(cur_x<n&&cur_y>=0) {
	    		if(matrix[cur_x][cur_y]==target) {
	    			return true;
	    		}else if(matrix[cur_x][cur_y]>target) {
	    			cur_y--;
	    		}else if(matrix[cur_x][cur_y]<target) {
	    			cur_x++;
	    		}
	    	}
	    	return false;
	    }
	}
}
