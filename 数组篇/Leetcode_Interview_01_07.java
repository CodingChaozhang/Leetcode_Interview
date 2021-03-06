class Solution {
	    public void rotate(int[][] matrix) {
	    	int start_x = 0;
	    	int start_y = 0;
	    	int end_x   = matrix.length-1;
	    	int end_y   = matrix[0].length-1;
	    	while(start_x<end_x) {
	    		process(matrix,start_x++,start_y++,end_x--,end_y--);
	    	}
	    }
	    
	    // 处理
	    public void process(int[][] matrix,int start_x,int start_y,int end_x,int end_y) {
	    	// 一共几步
	    	int steps = end_x-start_x;
	    	//开始
	    	for(int i=0;i<steps;i++) {
	    		int temp = matrix[start_x][start_y+i];
	    		matrix[start_x][start_y+i] = matrix[end_x-i][start_y];
	    		matrix[end_x-i][start_y] = matrix[end_x][end_y-i];
	    		matrix[end_x][end_y-i]   = matrix[start_x+i][end_y];
	    		matrix[start_x+i][end_y] = temp;
	    	}
	    	
	    }
	}