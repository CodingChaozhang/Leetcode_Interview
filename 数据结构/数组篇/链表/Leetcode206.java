
public class Leetcode206 {
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
	// 翻转链表
	class Solution {
	    // 解题思路：给其增加一个前  一个后
		public ListNode reverseList(ListNode head) {
			ListNode pre = null;
			ListNode next = null;
			while(head!=null) {
				// 先保存当前状态
				next = head.next;
				
				// 对当前节点处理 指向前一个
				head.next = pre;
				
				// 变量移动
				pre = head;
				
				head = next;
				
			}
			return pre;
	    }
	}
}
