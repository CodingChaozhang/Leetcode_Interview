
public class Leetcode021 {
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
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    	// 新链表
	    	ListNode dummy = new ListNode(-1);
	    	ListNode head = dummy;
	    	// 开始走
	    	while(l1!=null&&l2!=null) {
	    		if(l1.val>l2.val) {
	    			head.next = l1;
		    		l1 = l1.next;
	    		}else {
	    			head.next = l2;
		    		l2 = l2.next;
	    		}
	    		head = head.next;
	    	}
	    	// 如果还有剩余
	    	if(l1!=null) {
	    		head.next = l1;
	    	}
	    	if(l2!=null) {
	    		head.next = l2;
	    	}
	    	return dummy.next;
	    }
	}
}
