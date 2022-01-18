

// 分割链表
public class Leetcode725 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val =x;
		}
	}
	class Solution {
		 public ListNode[] splitListToParts(ListNode root, int k) {
		    	// 一共k份，每份的量为size/k + （1:0）
		    	int len = 0;
		    	ListNode node = root;
		    	while(node!=null) {
		    		len++;
		    		node = node.next;
		    	}
		    	ListNode[] res = new ListNode[k];
		    	int size = len/k;
		    	int rem = len%k;
		    	for(int i=0;i<k;i++) {
		    		ListNode dummy = new ListNode(-1);
		    		ListNode cur  = dummy;
		    		// 多大
		    		for(int j=0;j<size+(i<rem?1:0);j++) {
		    			cur.next = new ListNode(root.val);
	                    cur = cur.next;
		    			if(root!=null) {
		    				root = root.next;
		    			}
		    		}
		    		res[i] = dummy.next;
		    		
		    		
		    	}
		    	return res;
		    	
		    }
	}
}
