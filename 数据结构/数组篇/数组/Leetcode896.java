// 单调数列
public class Leetcode896 {
	class Solution {
	    public boolean isMonotonic(int[] A) {
	    	// 其有两个状态
	    	boolean incr = true, decr = true;
	    	for(int i=0;i<A.length-1;i++) {
	    		if(A[i]>A[i+1]) {
	    			incr = false;
	    		}
	    		if(A[i]<A[i+1]) {
	    			decr = false;
	    		}
	    	}
	    	return incr || decr;
	    }
	}
}
