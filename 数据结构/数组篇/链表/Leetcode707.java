//设计链表
public class Leetcode707 {
	// 单链表
	class ListNode{
		int val;
		ListNode next;
		public ListNode() {
			
		}
		public ListNode(int val) {
			this.val = val;
		}
	}
	class MyLinkedList {
		// 遍历
		ListNode head;
		int size;
	    /** Initialize your data structure here. */
	    public MyLinkedList() {
	    	head = new ListNode(-1);
	    	size = 0;
	    }
	    
	    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
	    public int get(int index) {
	    	if(index<0||index>=size) {
	    		return -1;
	    	}
	    	ListNode cur = head.next;
	    	for(int i=0;i<index;i++) {
	    		cur = cur.next;
	    	}
	    	return cur.val;
	    }
	    
	    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
	    public void addAtHead(int val) {
	    	addAtIndex(0,val);
	    }
	    
	    /** Append a node of value val to the last element of the linked list. */
	    public void addAtTail(int val) {
	    	addAtIndex(size,val);
	    }
	    // 添加
	    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
	    public void addAtIndex(int index, int val) {
	    	
	    	if(index>size) {
	    		return;
	    	}
	    	if(index<0) {
	    		index = 0;
	    	}
	    	ListNode prev = head;
	    	for(int i=0;i<index;i++) {
	    		prev = prev.next;
	    	}
	    	ListNode cur = new ListNode(val);
	    	cur.next = prev.next;
	    	prev.next = cur;
	    	size++;
	    }
	    
	    /** Delete the index-th node in the linked list, if the index is valid. */
	    public void deleteAtIndex(int index) {
	    	if(index<0 || index>=size) {
	    		return;
	    	}
	    	ListNode prev = head;
	    	for(int i=0;i<index;i++) {
	    		prev = prev.next;
	    	}
	    	prev.next = prev.next.next;
	    	
	    	size--;
	    }
	}

	/**
	 * Your MyLinkedList object will be instantiated and called as such:
	 * MyLinkedList obj = new MyLinkedList();
	 * int param_1 = obj.get(index);
	 * obj.addAtHead(val);
	 * obj.addAtTail(val);
	 * obj.addAtIndex(index,val);
	 * obj.deleteAtIndex(index);
	 */

}
