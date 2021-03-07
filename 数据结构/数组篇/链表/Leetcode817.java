// 链表组件
import java.util.*;
public class Leetcode817 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	class Solution {
	    public int numComponents(ListNode head, int[] G) {
	    	// 用hashset来辅助解题
	    	HashSet<Integer> hashset = new HashSet<>();
	    	for(int num:G) {
	    		hashset.add(num);
	    	}
	    	// 遍历
	    	ListNode node = head;
	    	int res = 0;
	    	while(node!=null) {
	    		if(hashset.contains(node.val)&&((node.next==null)|| !hashset.contains(node.next.val))) {
	    			res++;
	    		}
	    		node = node.next;
	    	}
	    	return res;
	    }
	}
}
