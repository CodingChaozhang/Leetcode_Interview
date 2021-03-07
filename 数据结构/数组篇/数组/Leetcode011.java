public class Leetcode011 {
	class Solution {
	    public int maxArea(int[] height) {
	    	// 双指针从两边开始移动
	    	int left = 0;
	    	int right = height.length-1;
	    	// 存储结果
	    	int res = 0;
	    	// 存储临时的值
	    	int temp = 0;
	    	while(left<right) {
	    		temp = (right-left)*Math.min(height[left], height[right]);
	    		res = Math.max(res, temp);
	    		// 更新步伐
	    		if(height[left]<height[right]) {
	    			left++;
	    		}else {
	    			right--;
	    		}
	    	}
	    	return res;
	    }
	}
}
