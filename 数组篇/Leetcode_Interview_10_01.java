// 合并排序的数组
public class Leetcode_10_01 {
	class Solution {
	    public void merge(int[] A, int m, int[] B, int n) {
	    	int start_A = m-1;
	    	int start_B = n-1;
	    	int index = m+n-1;
	    	// 开始 
	    	while(start_A>=0&&start_B>=0) {
	    		if(A[start_A]>=B[start_B]) {
	    			A[index] = A[start_A];
	    			index--;
	    			start_A--;
	    		}else {
	    			A[index] = B[start_B];
	    			index--;
	    			start_B--;
	    		}
	    	}
	    	// 如果B还没走完
	    	while(start_B>=0) {
	    		A[index--] = B[start_B--];
	    	}
	    	
	    }
	}
}
