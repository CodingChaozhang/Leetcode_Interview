// 翻转图像
public class Leetcode832 {
	class Solution {
		// 翻转图像考察逻辑=先水平翻转图像 后反转图像
	    public int[][] flipAndInvertImage(int[][] image) {
	    	int row = image.length-1;
	    	int col = image[0].length-1;
	    	// 开始遍历
	    	for(int i=0;i<=row;i++) {
	    		// 翻转图像
	    		reverse(image,i,0,col);	    		
	    	}
	    	return image;
	    }
	    
	    public void reverse(int[][] image,int row,int start_col,int end_col) {
	    	// 开始遍历
	    	while(start_col<=end_col) {
	    		int temp = image[row][start_col]==1?0:1;
	    		image[row][start_col] = image[row][end_col]==1?0:1;
	    		image[row][end_col]   = temp;
	    		// 开始走
	    		start_col++;
	    		end_col--;
	    	}
	    }
	}
}
