// K个一组翻转链表
public class Leetcode025 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
		ListNode(int val,ListNode next){
			this.val = val;
			this.next = next;
		}
	}
	class Solution {
	    // 多个k组合并成一个，一个成多个
		public ListNode reverseKGroup(ListNode head, int k) {
	    	ListNode dummy = new ListNode(-1,head);
	    	ListNode pre = dummy;
	    	ListNode tail = dummy;
	    	// 对其遍历
	    	while(head!=null) {
	    		for(int i=0;i<k;i++) {
	    			tail = tail.next;
	    			if(tail==null) {
	    				return dummy.next;
	    			}
	    		}
	    		ListNode start = pre.next;
	    		ListNode end = tail.next;
	    		
	    		tail.next = null;
	    		// 翻转
	    		pre.next = myReverse(start);
	    		start.next = end;
	    		
	    		pre = start;
	    		tail = pre;
	    		
	    	}
	    	
	    	return dummy.next;
	    }
		
		// 翻转
		public ListNode myReverse(ListNode head) {
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
