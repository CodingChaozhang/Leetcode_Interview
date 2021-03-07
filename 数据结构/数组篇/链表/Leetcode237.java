// 删除链表中的结点
public class Leetcode237 {
	// 链表
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	class Solution {
	    public void deleteNode(ListNode node) {
	        node.val = node.next.val;
	        node.next = node.next.next;
	    }
	}
}
