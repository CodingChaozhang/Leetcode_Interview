package com.lcz.leetcode;
// 删除链表的倒数第N个结点
public class Leetcode019 {
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
		// 删除链表的倒数第N个结点
	    public ListNode removeNthFromEnd(ListNode head, int n) {
	    	// 删除结点用哑结点
	    	ListNode dummy = new ListNode(0,head);
	    	// 解法用快慢指针
	    	ListNode slow = dummy;
	    	ListNode fast = dummy;
	    	// 开始走n步
	    	for(int i=0;i<n;i++) {
	    		fast = fast.next;
	    	}
	    	// 一起走
	    	while(fast.next!=null) {
	    		fast = fast.next;
	    		slow = slow.next;
	    	}
	    	// 删除
	    	slow.next = slow.next.next;
	    	return dummy.next;
	    }
	}
}
