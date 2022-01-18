
public class Leetcode086 {
	// 链表
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
		// 分割链表
	    public ListNode partition(ListNode head, int x) {
	    	ListNode smallHead = new ListNode(-1);
	    	ListNode small = smallHead;
	    	ListNode bigHead   = new ListNode(-1);
	    	ListNode big = bigHead;
	    	// 开始遍历
	    	while(head!=null) {
	    		// 判断
	    		if(head.val<x) {
	    			small.next = head;
	    			small = small.next;
	    		}else {
	    			big.next = head;
	    			big = big.next;
	    		}
	    		head = head.next;
	    	}
	    	big.next = null;
	    	small.next = bigHead.next;
	    	head = smallHead.next;
	    	return head;
	    }
	}
}
