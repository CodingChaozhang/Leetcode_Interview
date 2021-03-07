// N皇后II
public class Leetcode052 {
	class Solution {
        int res = 0;
	    public int totalNQueens(int n) {
	    	// 构建棋盘
	    	char[][] chess = new char[n][n];
	    	for(int i=0;i<n;i++) {
	    		for(int j=0;j<n;j++) {
	    			chess[i][j] = '.';
	    		}
	    	}
	    	// 开始回溯 第0行开始
	    	dfs(chess,0);
	    	return res;
	    }
	    
	    // 回溯
	    public void dfs(char[][] chess,int row) {
	    	// 递归结束条件
	    	if(row==chess.length) {
	    		res+=1;
	    		return;
	    	}
	    	// 回溯
	    	for(int col=0;col<chess[0].length;col++) {
	    		// 判断能否放下去
	    		if(valid(chess,row,col)) {
	    			chess[row][col] = 'Q';
	    			dfs(chess,row+1);
	    			chess[row][col] = '.';
	    		}
	    	}
	    }
	    
	    // 判断合法性
	    public boolean valid(char[][] chess,int row,int col) {
	    	// 判断其上面 其左上角 右上角
	    	
	    	for(int i=0;i<row;i++) {
	    		if(chess[i][col]=='Q') {
	    			return false;
	    		}
	    	}
	    	
	    	for(int i=row-1,j=col+1;i>=0&&j<chess[0].length;i--,j++) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	
	    	for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	}
}
