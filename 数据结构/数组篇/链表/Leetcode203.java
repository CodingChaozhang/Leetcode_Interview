
public class Leetcode203 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(){
			
		}
		ListNode(int val){
			this.val = val;
		}
		ListNode(int val,ListNode next){
			this.val = val;
			this.next = next;
		}
	}
	class Solution {
	    public ListNode removeElements(ListNode head, int val) {
	    	// 哑结点
	    	ListNode dummy = new ListNode(-1,head);
	    	ListNode prev = dummy;
	    	ListNode cur = head;
	    	while(cur!=null) {
	    		// 相等
	    		if(cur.val==val) {
	    			// 相等的话
	    			prev.next = cur.next;
	    			cur = cur.next;
	    		}else {
	    			prev.next = cur;
	    			cur = cur.next;
	    			prev = prev.next;
	    		}
	    		
	    	}
	    	return dummy.next;
	    }
	}
}
