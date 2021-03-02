// 最大连续1的个数III
public class Leetcode1004 {
	class Solution {
	    public int longestOnes(int[] A, int K) {
	    	// K即统计0的个数，因为K只是改变0
	    	int l = 0;
	    	int r = 0;
	    	int len = A.length;
	    	int zeros = 0;
	    	int res = 0;
	    	while(r<len) {
	    		if(A[r]==0) {
	    			zeros++;
	    		}
	    		r++;
	    		if(zeros>K) {
	    			if(A[l]==0) {
	    				zeros--;
	    			}
	    			l++;
	    		}
	    		res = Math.max(res, r-l+1);
	    	}
	    	return res;
	    }
	}
}
