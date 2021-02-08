//奇偶链表
public class Leetcode328 {
	class Solution {
	    public ListNode oddEvenList(ListNode head) {
	        if(head==null || head.next == null) {
	        	return head;
	        }
	        // 奇数的指针
	        ListNode p1 = head;
	        // 偶数链表以及偶数链表的指针
	        ListNode head2 = head.next;
	        ListNode p2 = head2;
	        // 遍历
	        while(p1.next!=null&&p2.next!=null) {
	        	p1.next = p2.next;
	        	p1 = p1.next;
	        	p2.next = p1.next;
	        	p2 = p2.next;
	        }
	        p1.next = head2;
	        return head;
	        
	    }
	}
}
