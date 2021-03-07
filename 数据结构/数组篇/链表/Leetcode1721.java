// 交换链表中的结点
public class Leetcode1721 {
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
	    public ListNode swapNodes(ListNode head, int k) {
	    	ListNode slow = head;
	    	ListNode fast = head;
	    	ListNode first = null;
	    	ListNode second = null;
	    	// 开始找一个k
	    	for(int i=1;i<k;i++) {
	    		fast = fast.next;
	    	}
	    	first = fast;
	    	// 开始找第二个k
	    	while(fast.next!=null) {
	    		fast = fast.next;
	    		slow = slow.next;
	    	}
	    	second = slow;
	    	// 交换两个值
	    	int temp = first.val;
	    	first.val = second.val;
	    	second.val = temp;
	    	return head;
	    }
	}
}
