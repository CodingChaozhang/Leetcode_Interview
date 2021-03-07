// 分割链表
public class Leetcode_Interview_02_04 {
	class Solution {
		// 类似于快速排序
	    public ListNode partition(ListNode head, int x) {
	    	ListNode smallHead = new ListNode(-1);
	    	ListNode small = smallHead;
	    	ListNode bigHead = new ListNode(-1);
	    	ListNode big = bigHead;
	    	// 开始遍历
	    	ListNode cur = head;
	    	while(cur!=null) {
	    		// 判断
	    		if(cur.val<x) {
	    			small.next = cur;
	    			small = small.next;
	    		}else {
	    			big.next = cur;
	    			big = big.next;
	    		}
	    		cur = cur.next;
	    	}
	    	big.next = null;
	    	small.next = bigHead.next;
	    	head = smallHead.next;
	    	return head;
	    }
	}
}
