
public class Leetcode_Interview_1721 {
	class Solution {
	    public int trap(int[] height) {
	        if(height==null || height.length==0){
	            return 0;
	        }
	        // 左边找最大  右边找最大 两边最小值
		    	int res = 0;
		    	// 两边没法盛水
		    	int[] left_max  = new int[height.length];
		    	int[] right_max = new int[height.length];
		    	//初始化
		    	left_max[0] = height[0];
		    	for(int i=1;i<height.length;i++) {
		    		left_max[i] = Math.max(left_max[i-1], height[i]);
		    	}
		    	
		    	right_max[height.length-1] = height[height.length-1]; 
		    	for(int i=height.length-2;i>=0;i--) {
		    		right_max[i] = Math.max(right_max[i+1], height[i]);
		    	}
		    	
		    	// 对其遍历
		    	for(int i=1;i<height.length-1;i++) {
		    		res += Math.min(left_max[i], right_max[i]) - height[i];
		    	}
		    	return res;
	    }
	}
}
