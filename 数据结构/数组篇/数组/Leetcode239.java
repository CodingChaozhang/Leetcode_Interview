// 滑动窗口最大值
import java.util.*;
public class Leetcode0239 {
	class Solution {
	    public int[] maxSlidingWindow(int[] nums, int k) {
	    	 // 判断
	    	if(nums==null || nums.length<2 || k==1) {
	    		return nums;
	    	}
	    	// 维护一个队头元素始终是队列最大
	    	Deque<Integer> queue = new LinkedList<>();
	    	// 结果数组
	    	int[] res = new int[nums.length-k+1];
	    	// 遍历
	    	for(int i=0;i<nums.length;i++) {
	    		
	    		// 放入的元素已经够窗口的值了 删除过期值
	    		while(!queue.isEmpty()&&queue.peekFirst()<=i-k) {
	    			queue.pollFirst();
	    		}
	    		// 判断放入的值是否大于
	    		while(!queue.isEmpty()&&nums[queue.peekLast()]<=nums[i]) {
	    			queue.pollLast();
	    		}
	    		// 放入
	    		queue.addLast(i);
	    		// 判断窗口长度是否大于等于k个的时候才开始计算
	    		if(i-k+1>=0) {
	    			res[i-k+1] = nums[queue.peekFirst()];
	    		}
	    	}
	    	return res;
	    }
	}
}
