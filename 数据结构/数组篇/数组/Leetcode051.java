// N皇后问题
import java.util.*;
public class Leetcode051 {
	class Solution {
	    public List<List<String>> solveNQueens(int n) {
	    	// 构建一个N*N的棋盘
	    	char[][] chess = new char[n][n];
	    	for(int i=0;i<n;i++) {
	    		for(int j=0;j<n;j++) {
	    			chess[i][j] = '.';
	    		}
	    	}
	    	// 结果存储
	    	List<List<String>> res = new ArrayList<>();
	    	//开始回溯解题 从棋盘第0行开始
	    	solve(res,chess,0);
	    	return res;
	    }
	    // 回溯解题
	    public void solve(List<List<String>> res,char[][] chess,int row) {
	    	// 递归截止条件
	    	if(row==chess.length) {
	    		// 将当前棋盘封装成想要的值
	    		res.add(construct(chess));
	    		return;
	    	}
	    	// 开始遍历列
	    	for(int col=0;col<chess[0].length;col++) {
	    		// 判断是否合法 合法才记录
	    		if(valid(chess,row,col)) {
	    			chess[row][col] = 'Q';
	    			solve(res,chess,row+1);
	    			chess[row][col] = '.';
	    		}
	    	}
	    	
	    }
	    // 判断是否合法
	    public boolean valid(char[][] chess,int row,int col) {
	    	// 保证其坐标上面位置没有皇后
	    	for(int i=0;i<row;i++) {
	    		if(chess[i][col]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 判断当前坐标的左上角对角线
	    	for(int i=row-1,j=col+1;i>=0&&j<chess[0].length;i--,j++) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 判断当前坐标的左上角对角线
	    	for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 都没问题
	    	return true;
	    }
	    
	    
	    // 棋盘封装成返回值
	    public List<String> construct(char[][] chess){
	    	List<String> path = new ArrayList<>();
	    	for(int i=0;i<chess.length;i++) {
	    		// 整行一起添加进来
	    		path.add(new String(chess[i]));
	    	}
	    	return path;
	    }
	}
}
