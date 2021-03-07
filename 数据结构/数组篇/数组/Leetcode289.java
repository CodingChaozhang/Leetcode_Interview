// 生命游戏
public class Leetcode289 {
	class Solution {
		// 解题思路 重点在于理解规则
	    public void gameOfLife(int[][] board) {
	    	// 统计每个点相邻的8个点，从而按照规则改变，但因为是同时改变，
	    	// 怕影响   所以活变成死为-1    死变成活的状态为2 其余看是否不变。
	    	int rows = board.length;
	    	int cols = board[0].length;
	    	// 开始遍历
	    	for(int row=0;row<rows;row++) {
	    		for(int col=0;col<cols;col++) {
	    			
	    			// 以便统计其活细胞数量
	    			int liveNum = 0;
	    			// 遍历其周围的8个点 即(坐标点-1 0 1)
	    			for(int i=-1;i<=1;i++) {
	    				for(int j=-1;j<=1;j++) {
	    					// 不要当前点
	    					if(!(i==0&&j==0)) {
	    						// 相邻点的坐标
	    						int new_r = row+i;
	    						int new_c = col+j;
	    						// 查看是否相邻的点坐标是否超出界限以及是否是活细胞 1 或者-1
	    						if((new_r<rows&&new_r>=0)&&(new_c<cols&&new_c>=0)&&(Math.abs(board[new_r][new_c])==1)) {
	    							liveNum++;
	    						}
	    					}
	    				}
	    			}
	    			
	    			// 按规则对当前点处理  
	    			// 活的 只有2-3个才维持继续活 其余变为死亡状态 
	    			// 死的 只有3个的时候 才能变成活的状态
	    			if(board[row][col]==1&&(liveNum<2||liveNum>3)) {
	    				board[row][col] = -1;
	    			}
	    			if(board[row][col]==0&&(liveNum==3)) {
	    				board[row][col] = 2;
	    			}
	    			
	    		}
	    	}
	    	
	    	// 最后对这些状态统一处理
	    	for(int row=0;row<rows;row++) {
	    		for(int col=0;col<cols;col++) {
	    			if(board[row][col]>0) {
	    				board[row][col]=1;
	    			}else {
	    				board[row][col] = 0;
	    			}
	    		}
	    	}
	    	
	    	
	    }
	}
}
