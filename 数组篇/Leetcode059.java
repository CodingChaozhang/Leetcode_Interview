// 螺旋矩阵II
public class Leetcode059 {
	class Solution {
		// 全局变量记录
		int index = 1;
	    public int[][] generateMatrix(int n) {
	    	// 第一步生成矩阵
	    	int[][] arr = new int[n][n];
	    	// 第二步螺旋
	    	int start_x = 0;
	    	int start_y = 0;
	    	int end_x = n-1;
	    	int end_y = n-1;
	    	while(start_x<=end_x) {
	    		process(arr,start_x++,start_y++,end_x--,end_y--);
	    	}
	    	return arr;
	    }
	    // 开始处理
	    public void process(int[][] arr,int start_x,int start_y,int end_x,int end_y) {
	    	// 开始列遍历
	    	for(int i=start_y;i<=end_y;i++) {
	    		arr[start_x][i] = index++;
	    	}
	    	// 开始行遍历
	    	for(int i=start_x+1;i<=end_x;i++) {
	    		arr[i][end_y] = index++;
	    	}
	    	//判断一下
	    	if(start_x==end_x||start_y==end_y) {
	    		return;
	    	}
	    	// 返回列
	    	for(int i=end_y-1;i>=start_y;i--) {
	    		arr[end_x][i] = index++;
	    	}
	    	// 返回行
	    	for(int i=end_x-1;i>start_x;i--) {
	    		arr[i][start_y] = index++;
	    	}
	    	
	    }
	}
}
