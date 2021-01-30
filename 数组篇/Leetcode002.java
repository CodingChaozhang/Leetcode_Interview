// 两数相加
public class Leetcode002 {
	class ListNode {
		int val;
		 ListNode next;
		 ListNode() {}
		 ListNode(int val) { this.val = val; }
		 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	class Solution {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    	ListNode head = null;
	    	ListNode tail = null;
	    	// 加法运算开始
	    	int sum = 0;
	    	int carry = 0;
	    	while(l1!=null || l2!=null) {
	    		// 判断是否有为空的
	    		int x = l1!=null?l1.val:0;
	    		int y = l2!=null?l2.val:0;
	    		
	    		// 开始相加
	    		sum = x + y + carry;
	    		carry = sum/10;
	    		if(head==null) {
	    			head=tail=new ListNode(sum%10);
	    		}else {
	    			tail.next = new ListNode(sum%10);
	    			tail = tail.next;
	    		}
	    		// 继续走
	    		l1 = l1.next;
	    		l2 = l2.next;

	    	}
	    	// 判断是否还有余数
	    	if(carry!=0) {
	    		tail = new ListNode(sum%10);
	    	}
	    	return head;
	    }
	}
}
