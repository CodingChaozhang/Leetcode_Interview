
public class Leetcode_Interview_16_04 {
	class Solution {
	    public String tictactoe(String[] board) {
	    	// 不需要放了，直接考察
	    	// 统计空格  统计当前字符的上下两线，统计整个对角线
	    	boolean flag = false;
	    	int heng = 0;
	    	int zong = 0;
	    	int left = 0;
	    	int right = 0;
	    	// 对其遍历
	    	for(int i=0;i<board.length;i++) {
	    		heng = 0;
	    		zong = 0;
	    		for(int j=0;j<board.length;j++) {
	    			if(board[i].charAt(j)==' ') {
	    				flag = true;
	    			}
	    			// 统计当前字符的横和纵
	    			heng = heng + (int)board[i].charAt(j);
	    			zong = zong + (int)board[j].charAt(i);
	    		}
	    		// 检查横和纵
	    		if(heng==(int)'X'*board.length || zong == (int)'X'*board.length) {
	    			return "X";
	    		}
	    		if(heng==(int)'O'*board.length || zong== (int)'O'*board.length) {
	    			return "O";
	    		}
	    		// 对角线
	    		left = left + (int)board[i].charAt(i);
	    		right = right + (int)board[i].charAt(board.length-i-1);
	  
	    	}
	    	// 检查对角线
	    	if(left==(int)'X'*board.length || right==(int)'X'*board.length) {
	    		return "X";
	    	}
	    	
	    	if(left==(int)'O'*board.length || right==(int)'O'*board.length) {
	    		return "O";
	    	}
	    	
	    	if(flag) {
	    		return "Pending";
	    	}else {
	    		return "Draw";
	    	}
	    	
	    }
	}
}
