// 翻转链表
public class Leetcode_Offer_24 {
	// 链表
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	class Solution {
	    public ListNode reverseList(ListNode head) {
	    	// 开始
	    	ListNode pre = null;
	    	ListNode next = null;
	    	while(head!=null) {
	    		next = head.next;
	    		head.next = pre;
	    		pre = head;
	    		head = next;
	    	}
	    	return pre;
	    }
	}
}
