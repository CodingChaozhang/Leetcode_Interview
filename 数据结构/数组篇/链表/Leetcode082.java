// 删除排序链表中的重复元素II
public class Leetcode082 {
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
	    public ListNode deleteDuplicates(ListNode head) {
	    	//哑结点
	    	ListNode dummy = new ListNode(-1,head);
	    	// 快慢指针解题
	    	ListNode slow = dummy;
	    	ListNode fast = head;
	    	while(fast!=null) {
	    		// 重复元素了
	    		if(fast.next!=null&&fast.val==fast.next.val) {
	    			// 就一直走
	    			while(fast.next!=null&&fast.val==fast.next.val) {
	    				fast = fast.next;
	    			}
	    			fast = fast.next;
	    			slow.next = fast;
	    		}else {
	    			// 没有重复元素
	    			slow = slow.next;
	    			fast = fast.next;
	    		}
	    	}
	    	return dummy.next;
	    }
	}
}
