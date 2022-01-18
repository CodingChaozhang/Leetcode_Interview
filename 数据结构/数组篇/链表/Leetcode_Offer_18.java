// 删除链表的结点
public class Leetcode_Offer_18 {
	// 结点
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
		
	}
	class Solution {
	    public ListNode deleteNode(ListNode head, int val) {
	    	// 开一个哑结点
	    	ListNode dummy = new ListNode(-1);
	    	dummy.next = head;
	    	ListNode prev = dummy;
	    	ListNode cur = head;
	    	while(cur!=null) {
	    		// 看是否
	    		if(cur.val == val) {
	    			prev.next = cur.next;
	    			break;
	    		}
	    		// 不相等
	    		prev = cur;
	    		cur = cur.next;
	    	}
	    	return dummy.next;
	    }
	}
}
