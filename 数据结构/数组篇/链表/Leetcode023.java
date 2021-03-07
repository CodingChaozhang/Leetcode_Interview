// 合并k个升序链表
import java.util.*;
public class Leetcode023 {
	class Solution {
		class ListNode{
			int val;
			ListNode next;
			ListNode(){
				
			}
			ListNode(int val){
				this.val = val;
			}
			ListNode(int val,ListNode next){
				this.val  = val;
				this.next = next;
			}
		}
		// 对链表做一个封装
		class Status implements Comparable<Status>{
			ListNode node;
			int val;
			Status(ListNode node,int val){
				this.node = node;
				this.val = val;
			}
			public int compareTo(Status s2) {
				return this.val - s2.val;
			}
		}
		
	    public ListNode mergeKLists(ListNode[] lists) {
	    	// 开始合并
	    	ListNode dummy = new ListNode(-1);
	    	ListNode head  = dummy;
	    	// 优先级队列
	    	PriorityQueue<Status> queue = new PriorityQueue<Status>();
	    	// 先存放
	    	for(int i=0;i<lists.length;i++) {
	    		if(lists[i]!=null) {
	    			queue.offer(new Status(lists[i],lists[i].val));
	    		}
	    	}
	    	// 开始取
	    	while(!queue.isEmpty()) {
	    		Status status = queue.poll();
	    		ListNode node = status.node;
	    		
	    		// 合并
	    		head.next = node;
	    		head = head.next;
	    		// 看是否还有点
	    		if(node.next!=null) {
	    			queue.offer(new Status(node.next,node.next.val));
	    		}
	    		
	    	}
	    	
	    	return dummy.next;
	    }
	}
}
