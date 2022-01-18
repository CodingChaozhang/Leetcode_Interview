// 从尾到头打印链表
public class Leetcode_Offer_06 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
		}
	}
	
	class Solution {
	    public int[] reversePrint(ListNode head) {
	    	// 统计链表长度
	    	int len = 0;
	    	ListNode newHead = head;
	    	while(newHead !=null) {
	    		len++;
	    		newHead = newHead.next;
	    	}
	    	// 开始遍历
	    	int[] res = new int[len];
	    	for(int i=len-1;i>=0;i--) {
	    		res[i] = head.val;
	    		head = head.next;
	    	}
	    	return res;
	    }
	}
}
