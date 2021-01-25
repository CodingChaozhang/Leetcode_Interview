// 回溯解题  

public class Leetcode079 {
	class Solution {
	    public boolean exist(char[][] board, String word) {
	    	char[] words = word.toCharArray();
	    	// 其开头位置不确定
	    	for(int i=0;i<board.length;i++) {
	    		for(int j=0;j<board[0].length;j++) {
	    			if(dfs(board,words,i,j,0)) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
	    }
	    
	    // 回溯
	    public boolean dfs(char[][] board,char[] words,int i,int j,int index) {
	    	// 递归结束条件
	    	if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=words[index]) {
	    		return false;
	    	}
	    	// 结束的话就是
	    	if(index==words.length) {
	    		return true;
	    	}
	    	// 看下一个
	    	char temp = board[i][j];
	    	// 记录当前已经被遍历过了
	    	board[i][j] = '0';
	    	boolean res = dfs(board,words,i,j+1,index+1) || dfs(board,words,i,j-1,index+1) 
	    			    ||dfs(board,words,i+1,j,index+1) || dfs(board,words,i-1,j,index+1);
	    	// 恢复
	    	board[i][j] = temp;
	    	return res;
	    	
	    }
	}
}
