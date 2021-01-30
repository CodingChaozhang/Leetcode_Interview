import java.util.*;
public class Leetcode989 {
	class Solution {
	    public List<Integer> addToArrayForm(int[] A, int K) {
	    	// 加法都是从后往前加的
	    	
	    	// 结果存储
	    	List<Integer> res = new ArrayList<>();
	    	// 加法
	    	int  i = A.length-1;
	    	int sum = 0;
	    	int carry = 0;
	    	while(i>=0&&K!=0) {
	    		//当前值
	    		int x = i>=0?A[i]:0;
	    		int y = K!=0?K%10:0;
	    		
	    		// 加起来
	    		sum = x+y+carry;
	    		carry = sum/10;
	    		// 继续移动A两数
	    		i--;
	    		K = K/10;
	    		
	    		// 结果存储
	    		res.add(0,sum%10);
	    	}
	    	// 判断是否有余数
	    	if(carry!=0) {
	    		res.add(0,carry);
	    	}
	    	return res;
	    }
	}
}
