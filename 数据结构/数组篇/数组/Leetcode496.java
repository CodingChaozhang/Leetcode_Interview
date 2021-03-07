// 下一个更大的元素 I
import java.util.*;
public class Leetcode496 {
	class Solution {
	    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
	    	// 单调栈
	    	// 现在nums2中遍历 得到每一个比起大的元素
	    	// 结果用HashMap来存储
	    	HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
	    	Deque<Integer> stack = new ArrayDeque<>();
	    	// 对nums2遍历 单调栈的方法
	    	for(int i=0;i<nums2.length;i++) {
	    		// 出栈
	    		while(!stack.isEmpty()&&nums2[i]>nums2[stack.peek()]) {
	    			int curIndex = stack.pop();
	    			// 存储
	    			hashMap.put(nums2[curIndex],nums2[i]);
	    		}
	    		// 入栈
	    		stack.push(i);
	    	}
	    	// 单调栈中其实还有一些值
	    	while(!stack.isEmpty()) {
	    		hashMap.put(nums2[stack.pop()],-1);
	    	}
	    	// 结果存储
	    	int[] res = new int[nums1.length];
	    	// 开始遍历num1找其值
	    	for(int i=0;i<nums1.length;i++) {
	    		if(hashMap.containsKey(nums1[i])) {
	    			//包含该值则
	    			res[i] = hashMap.get(nums1[i]);
	    		}
	    	}
	    	return res;
	    }
	}
}
