
// 跳跃游戏
public class Leetcode55 {
	class Solution {
	    public boolean canJump(int[] nums) {
	    	// 贪心算法
	    	int rightMax = 0;
	    	int length = nums.length -1;
	    	// 对其遍历
	    	for(int i=0;i<length;i++) {
	    		// 其最大长度可以遍历到
	    		if(i<=rightMax) {
	    			// 更新最长长度
	    			rightMax = Math.max(rightMax, i+nums[i]);
	    			// 判断最长长度是否已超过数组长度
	    			if(rightMax>=length) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
	    }
	}
	    
}
