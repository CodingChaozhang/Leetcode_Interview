// 二进制链表转整数
import java.util.*;
public class Leetcode1290 {
	class Solution {
	    public int getDecimalValue(ListNode head) {
	    	if(head.next==null) {
	    		return head.val;
	    	}
	    	// 用栈来辅助
	    	Stack<Integer> stack = new Stack<>();
	    	while(head!=null) {
	    		stack.push(head.val);
	    		head = head.next;
	    	}
	    	// 结果存储
	    	int res = 0;
	    	int k = 0;
	    	// 对栈进行遍历
	    	while(!stack.isEmpty()) {
	    		int temp = (stack.pop())*(int)Math.pow(2, k++); 
	    		res += temp;
	    	}
	    	return res;
	    }
	}
}
