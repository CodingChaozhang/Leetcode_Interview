// 旋转链表
public class Leetcode061 {
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
	    public ListNode rotateRight(ListNode head, int k) {
	    	if(head==null || head.next == null) {
	    		return head;
	    	}
	    	// 1.使其变为环形链表
	    	ListNode tail = head;
	    	int len = 1;
	    	while(tail.next!=null) {
	    		len++;
	    		tail = tail.next;
	    	}
	    	tail.next = head;
	    	// 2.到尾结点这里
	    	k = k % len;
	    	for(int i=0;i<len-k;i++) {
	    		tail = tail.next;
	    	}
	    	//3.找到头结点
	    	head = tail.next;
	    	tail.next = null;
	    	return head;
	    }
	}
}
