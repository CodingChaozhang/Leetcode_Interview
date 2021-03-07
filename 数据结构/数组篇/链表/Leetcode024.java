// 两两交换链表中的结点
public class Leetcode024 {
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
		// 递归方法
	    public ListNode swapPairs(ListNode head) {
	    	// 递归结束的条件
	    	if(head==null || head.next==null) {
	    		return head;
	    	}
	    	ListNode newHead = head.next;
	    	head.next = swapPairs(newHead.next);
	    	newHead.next = head;
	    	return newHead;
	    }
	    // 迭代方法
	    public ListNode swapPairs_2(ListNode head) {
	    	 ListNode dummy = new ListNode(-1,head);
		    	ListNode newHead = dummy;
		    	// 开始遍历
		    	while(newHead.next!=null&&newHead.next.next!=null) {
		    		ListNode node1 = newHead.next;
		    		ListNode node2 = newHead.next.next;
		    		// 交换
		    		newHead.next = node2;
		    		node1.next = node2.next;
		    		node2.next = node1;
		    		// 移动
		    		newHead = node1;
		    	}	    	
		    	return dummy.next;
	    }

	}
}
