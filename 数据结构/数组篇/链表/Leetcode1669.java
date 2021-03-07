// 合并两个链表
public class Leetcode1669 {
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
	    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
	    	ListNode prev = list1;
	    	// 开始
	    	for(int i=1;i<a;i++) {
	    		prev = prev.next;
	    	}
	    	ListNode next = prev.next;
	    	// 继续走
	    	for(int i=a;i<=b;i++) {
	    		next = next.next;
	    	}
	    	// 第二个链表
	    	ListNode h2 = list2;
	    	ListNode t2 = list2;
	    	while(t2.next!=null) {
	    		t2 = t2.next;
	    	}
	    	// 拼接
	    	prev.next = h2;
	    	t2.next = next;
	    	return list1;
	    }
	}
}
