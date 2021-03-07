// 移除重复结点
import java.util.*;
public class Leetcode_Interview_02_01 {
	// 结点
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	class Solution {
		// 用hashSet
	    public ListNode removeDuplicateNodes(ListNode head) {
	    	if(head==null||head.next==null) {
	    		return head;
	    	}
	    	HashSet<Integer> hashset = new HashSet<>();
	    	ListNode dummy = new ListNode(-1);
	    	dummy.next = head;
	    	ListNode node = dummy;
	    	while(node.next!=null) {
	    		// 不包含该值
	    		if(!hashset.contains(node.next.val)) {
	    			hashset.add(node.next.val);
	    			node = node.next;
	    		}else {
	    			// 包含该值
	    			node.next = node.next.next;
	    		}
	    		
	    	}
	    	
	    	return dummy.next;
	    }
	}
}
