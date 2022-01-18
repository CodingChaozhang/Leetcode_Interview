// 链表的中间结点
public class Leetcode0876 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	class Solution {
	    public ListNode middleNode(ListNode head) {
	    	// 双指针
	    	ListNode fast = head;
	    	ListNode low = head;
	    	// 一起走
	    	while(fast.next!=null&&fast.next.next!=null) {
	    		fast = fast.next.next;
	    		low  = low.next;
	    	}
	    	return low;
	    }
	}
}
