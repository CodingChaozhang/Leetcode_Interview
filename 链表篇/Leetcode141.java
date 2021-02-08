
public class Leetcode0141 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
			this.next = null;
		}
	}
	public class Solution {
		// 双指针来解决环形链表
	    public boolean hasCycle(ListNode head) {
	    	if(head==null||head.next==null) {
	    		return false;
	    	}	    		
	    	ListNode fast = head;
	    	ListNode low = head;
	    	while(fast.next!=null&&fast.next.next!=null) {
	    		fast = fast.next.next;
	    		low = low.next;
		    	// 对其判断
	    		if(low==fast) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	}
}
