public class Leetcode066 {
	class Solution {
	    // 直接通过数组
	    public int[] plusOne_2(int[] digits) {
	    	// 为防止超出界限
	    	int flag = 1;
	    	for(int i=digits.length-1;i>=0;i--) {
	    		if(digits[i]+flag>=10) {
	    			digits[i] = 0;
	    			flag = 1;
	    		}else {
	    			digits[i]  = digits[i] + flag;
	    			flag = 0;
	    		}
	    	}
	    	// 查看此时的flag
	    	if(flag!=0) {
	    		// 还有余数
	    		int[] new_digits = new int[digits.length+1];
	    		new_digits[0]    = 1;
	    		return new_digits;
	    	}else {
	    		return digits;
	    	}
	    }

	}
}
