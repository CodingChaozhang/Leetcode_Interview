// 环路检测
public class Leetcode_Interview_02_08 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
		}
	}
	public class Solution {
	    public ListNode detectCycle(ListNode head) {
	        if(head==null||head.next==null) {
	        	return null;
	        }
	        // 快慢指针
	        ListNode slow = head;
	        ListNode fast = head;
	        boolean isCycle = false;
	        while(fast!=null&&fast.next!=null) {
	        	fast = fast.next.next;
	        	slow = slow.next;
	        	if(slow==fast) {
	        		isCycle=true;
	        		break;
	        	}
	        }
	        fast = head;
	        // 如果有环
	        if(isCycle) {
	        	while(fast!=slow) {
	        		fast = fast.next;
	        		slow = slow.next;
	        	}
	        	return slow;
	        }else {
	        	return null;
	        }
	        
	    }
	}
}
