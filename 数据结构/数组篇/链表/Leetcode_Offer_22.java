// 链表中倒数第k个结点
public class Leetcode_Offer_22 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
		}
	}
	
	class Solution {
	    public ListNode getKthFromEnd(ListNode head, int k) {
	    	// 哑指针
	    	ListNode dummy = new ListNode(-1);
	    	dummy.next = head;
	    	ListNode fast = dummy;
	    	ListNode low = dummy;
	    	// 开始走
	    	for(int i=0;i<k;i++) {
	    		fast = fast.next;
	    	}
	    	// 一起走
	    	while(fast.next!=null) {
	    		low = low.next;
	    		fast = fast.next;
	    	}
	    	return low.next;
	    }
	}
}
