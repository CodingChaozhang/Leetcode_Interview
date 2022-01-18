// 两数相加II
import java.util.*;
public class Leetcode445 {
	class Solution {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    	// 用栈来辅助解题
	    	Stack<Integer> s1 = new Stack<>();
	    	Stack<Integer> s2 = new Stack<>();
	    	while(l1!=null) {
	    		s1.push(l1.val);
	    		l1 = l1.next;
	    	}
	    	while(l2!=null) {
	    		s2.push(l2.val);
	    		l2 = l2.next;
	    	}
	    	// 余数
	    	int carry = 0;
	    	// 结果
	    	ListNode res = null;
	    	// 开始遍历
	    	while(!s1.isEmpty()||!s2.isEmpty()) {
	    		int x = s1.isEmpty()?0:s1.pop();
	    		int y = s2.isEmpty()?0:s2.pop();
	    		// 求和
	    		int sum = x+y+carry;
	    		carry = sum/10;
	    		// 结点
	    		ListNode curNode = new ListNode(sum%10);
	    		curNode.next = res;
	    		res = curNode;
	    	}
	    	return res;
	    }
	}
}
