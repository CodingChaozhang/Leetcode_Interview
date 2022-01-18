// 返回倒数第K个结点
public class Leeetcode_Interview_02_02 {
	// 结点
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	class Solution {
	    public int kthToLast(ListNode head, int k) {
	    	ListNode low = head;
	    	ListNode fast = head;
	    	// 先走快的
	    	for(int i=0;i<k;i++) {
	    		fast = fast.next;
	    	}
	    	// 后来一起走
	    	while(fast!=null) {
	    		low = low.next;
	    		fast = fast.next;
	    	}
	    	return low.val;
	    }
	}
}
