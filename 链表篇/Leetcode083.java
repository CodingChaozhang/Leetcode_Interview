// 删除排序链表中的重复元素
public class Leetcode083 {
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
		// 删除排序链表中的重复元素
	    public ListNode deleteDuplicates(ListNode head) {
	    	if(head==null || head.next == null) {
	    		return head;
	    	}
	    	ListNode slow = head;
	    	ListNode fast = head;
	    	while(fast!=null) {
	    		// 重复的话就一直走
	    		while(fast!=null&&slow.val == fast.val) {
	    			fast = fast.next;
	    		}
	    		// 不重复了
	    		slow.next = fast;
	    		slow = fast;
                if(fast!=null){
	    		    fast = fast.next;
                }
	    	}
	    	return head;
	    }
	}
}
