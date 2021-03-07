// 回文链表
import java.util.*;
public class Leetcode_Interview_02_06 {
	class Solution {
		// 解题思路用栈
	    public boolean isPalindrome(ListNode head) {
	    	Stack<Integer> stack = new Stack<>();
	    	ListNode node = head;
	    	while(node!=null) {
	    		stack.push(node.val);
	    		node = node.next;
	    	}
	    	
	    	// 在遍历一次
	    	node = head;
	    	while(node!=null) {
	    		if(node.val!=stack.pop()) {
	    			return false;
	    		}
	    		node = node.next;
	    	}
	    	return true;
	    }
	}
}
