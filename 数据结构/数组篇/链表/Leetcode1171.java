import java.util.*;
public class Leetcode1171 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	class Solution {
	    public ListNode removeZeroSumSublists(ListNode head) {
	    	// 类似于前缀和 连续的和
	    	HashMap<Integer,ListNode> hashMap = new HashMap<>();
	    	ListNode dummy = new ListNode(0);
	    	dummy.next = head;
	    	int sum = 0;
	    	// 对其遍历
	    	for(ListNode node=dummy;node!=null;node=node.next) {
	    		sum += node.val;
	    		hashMap.put(sum,node);
	    	}
	    	// 第二次遍历
	    	sum = 0;
	    	for(ListNode node=dummy;node!=null;node=node.next) {
	    		sum += node.val;
	    		node.next = hashMap.get(sum).next;
	    	}
	    	return dummy.next;
	    }
	}
}
