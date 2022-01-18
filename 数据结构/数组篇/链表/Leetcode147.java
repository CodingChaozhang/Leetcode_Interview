// 对链表进行插入排序
public class Leetcode0147 {
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
	    public ListNode insertionSortList(ListNode head) {
	    	if(head==null || head.next == null) {
	    		return head;
	    	}
	    	// 哑结点
	    	ListNode dummy = new ListNode(-1,head);
	    	// 遍历结束
	    	while(head!=null&&head.next!=null) {
	    		// 先找到没拍好序的
	    		if(head.val<=head.next.val) {
	    			head = head.next;
	    			continue;
	    		}
	    		// 找到要插入的位置
	    		ListNode pre = dummy;
	    		while(pre.next.val<head.next.val) {
	    			pre = pre.next;
	    		}
	    		ListNode cur = head.next;
	    		//
	    		head.next = cur.next;
	    		cur.next = pre.next;
	    		pre.next = cur;	    		
	    	}
	    	return dummy.next;
	    }
	}
}
