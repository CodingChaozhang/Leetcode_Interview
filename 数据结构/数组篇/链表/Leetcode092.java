// 反转链表II
public class Leetcode092 {
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
	    public ListNode reverseBetween(ListNode head, int m, int n) {
	    	// 哑结点
	    	ListNode dummy = new ListNode(-1,head);
	    	ListNode superior = dummy;
	    	for(int i=1;i<m;i++) {
	    		superior = superior.next;
	    	}
	    	ListNode cur = superior.next;
	    	ListNode prev = null;
	    	// 开始转换
	    	for(int i=m;i<=n;i++) {
	    		ListNode next = cur.next;
	    		cur.next = prev;
	    		prev = cur;
	    		cur = next;
	    	}
	    	// 更改m和m-n结点
	    	superior.next.next = cur;
	    	superior.next = prev;
	    	return dummy.next;
	    }
	}
}
