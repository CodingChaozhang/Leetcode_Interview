// 跳跃游戏II
public class Leetcode045 {
	class Solution {
		// 与之前的贪心算法 很相似
	    public int jump(int[] nums) {
	    	int rightMax = 0;
	    	int end  = 0;
	    	// 结果
	    	int step = 0;
	    	// 开始遍历
	    	for(int i=0;i<nums.length-1;i++) {
	    		// 增加判断条件而已
	    		rightMax = Math.max(rightMax, i+nums[i]);
	    		if(i==end) {
	    			end = rightMax;
	    			step++;
	    		}
	    	}
	    	return step;
	    }
	}
}
