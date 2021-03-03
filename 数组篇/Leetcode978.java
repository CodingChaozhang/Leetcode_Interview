
public class Leetcode978 {
	class Solution {
	    public int maxTurbulenceSize(int[] arr) {
	    	// 初始两个状态
	    	int down = 1;
	    	int up   = 1;
	    	// 结果
	    	int res  = 1;
	    	for(int i=1;i<arr.length;i++) {
	    		if(arr[i]>arr[i-1]) {
	    			up = down+1;
	    			down = 1;
	    		}else if(arr[i]<arr[i-1]) {
	    			down = up+1;
	    			up = 1;
	    		}else if(arr[i]==arr[i-1]) {
	    			down = 1;
	    			up = 1;
	    		}
	    		res = Math.max(res, Math.max(up,down));
	    	}
	    	return res;
	    }
	}
}
