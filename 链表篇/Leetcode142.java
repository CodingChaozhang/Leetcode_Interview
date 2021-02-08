
public class Leetcode142 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
			next = null;
		}
	}
	public class Solution {
	    // 双指针
		public ListNode detectCycle(ListNode head) {
			if(head==null||head.next==null) {
				return null;
			}
	        ListNode low = head;
	        ListNode fast = head;
	        // 是否有环形
	        boolean isCycle = false;
	        // 双指针走
	        while(fast.next!=null&&fast.next.next!=null) {
	        	fast = fast.next.next;
	        	low  = low.next;
	        	if(low==fast) {
	        		isCycle = true;
	        		break;
	        	}
	        }
	        // 如果有环形
	        if(isCycle) {
	        	fast = head;
	        	while(fast!=low) {
	        		low = low.next;
	        		fast = fast.next;
	        	}
	        	return low;
	        	
	        }else {
	        	return null;
	        }
	    }
	}
}
