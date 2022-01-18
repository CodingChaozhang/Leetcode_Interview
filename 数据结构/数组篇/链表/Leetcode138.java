import java.util.*;
public class Leetcode138 {
	// 结点
	class Node{
		int val;
		Node next;
		Node random;
		
		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
	class Solution {
	    public Node copyRandomList(Node head) {
	        // 解题思路用hashMap
	    	HashMap<Node,Node> hashMap = new HashMap<>();
	    	Node node = head;
	    	while(node!=null) {
	    		hashMap.put(node,new Node(node.val));
	    		node = node.next;
	    	}
	    	//继续赋值
	    	node = head;
	    	while(node!=null) {
	    		hashMap.get(node).next = hashMap.get(node.next);
	    		hashMap.get(node).random = hashMap.get(node.random);
	    		node = node.next;	    		
	    	}
	    	
	    	// 返回值
	    	return hashMap.get(head);
	    	
	    }
	}
}
