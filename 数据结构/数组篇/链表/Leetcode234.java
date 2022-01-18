// 回文链表
public class Leetcode0234 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	class Solution {
		// 回文链表判断
	    public boolean isPalindrome(ListNode head) {
	    	if(head==null || head.next == null) {
	    		return true;
	    	}
	    	// 双指针来判断
	    	ListNode fast = head;
	    	ListNode low  = head;
	    	while(fast.next!=null&&fast.next.next!=null) {
	    		low = low.next;
	    		fast = fast.next.next;
	    	}
	    	
	    	// 分成两个链表
	    	fast = low.next;
	    	low.next = null;
	    	// 将后面那个链表反转
	    	ListNode pre = null;
	    	ListNode next = null;
	    	while(fast!=null) {
	    		next = fast.next;
	    		fast.next = pre;
	    		pre = fast;
	    		fast = next;
	    	}
	    	// 其头结点改为pre 另外一个头结点为head
	    	while(head!=null&&pre!=null) {
	    		if(pre.val!=head.val) {
	    			return false;
	    		}
	    		pre = pre.next;
	    		head = head.next;
	    	}
	    	return true;
	    	
	    }
	}
}
