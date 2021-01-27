
public class Leetcode922 {
	class Solution {
	    public int[] sortArrayByParityII(int[] A) {
	    	int left = 0;
	    	int right = 1;
	    	while(left<A.length-1&&right<A.length) {
	    		// 满足条件移动到一个
	    		// 满足偶数
	    		while(left<A.length-1&&A[left]%2==0) {
	    			left += 2;
	    		}
	    		// 满足奇数
	    		while(right<A.length&&A[right]%2==1) {
	    			right += 2;
	    		}
	    		// 存在不满足的
	    		if(left<A.length-1&&right<A.length) {
	    			int temp = A[left];
	    			A[left]  = A[right];
	    			A[right] = temp;
	    		}
	    	}
	    	return A;
	    }
	}
}
