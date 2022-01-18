// 链表求和
import java.util.*;
public class Leetcode_Interview_02_05 {
	class Solution {
		// 正序遍历
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    	ListNode dummy = new ListNode(-1);
	    	ListNode node = dummy;
	    	// 开始遍历
	    	int carry = 0;
	    	while(l1!=null||l2!=null) {
	    		int x = l1==null?0:l1.val;
	    		int y = l2==null?0:l2.val;
	    		
	    		int sum = x+y+carry;
	    		carry = sum/10;
	    		
	    		ListNode cur = new ListNode(sum%10);
	    		node.next = cur;
	    		node = cur;
	    		// 走路
	    		if(l1!=null) {
	    			l1 = l1.next;
	    		}
	    		if(l2!=null) {
	    			l2 = l2.next;
	    		}

	    	}
	    	// 如果还有剩余
	    	if(carry!=0) {
	    		node.next = new ListNode(carry);
	    	}
	    	
	    	return dummy.next;
	    }
	}
}
