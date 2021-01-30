// 有效的山脉数组
public class Leetcode941 {
	class Solution {
	    // 双指针解题
		public boolean validMountainArray(int[] arr) {
	    	int len = arr.length;
	    	if(len<3) {
	    		return false;
	    	}
	    	// 第二条 同时从两边走  判断中间位置即可了
	    	int left = 0;
	    	int right = len-1;
	    	// 先走上坡路
	    	while(left<len-1&&arr[left]<arr[left+1]) {
	    		left++;
	    	}
	    	// 下坡路也走
	    	while(right>=1&&arr[right]<arr[right-1]) {
	    		right--;
	    	}
	    	
	    	// 排除特殊情况
	    	if(left==0||right==len-1) {
	    		return false;
	    	}
	    	return left==right;
	    }
	}
}
