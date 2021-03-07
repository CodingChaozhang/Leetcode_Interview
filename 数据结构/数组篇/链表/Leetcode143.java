// 重排链表
import java.util.*;
public class Leetcode143 {
	class Solution {
		// 用arraylist辅助结构来解题
	    public void reorderList(ListNode head) {
	        if(head==null) {
	        	return;
	        }
	        List<ListNode> list = new ArrayList<ListNode>();
	        ListNode node = head;
	        while(node!=null) {
	        	list.add(node);
	        	node = node.next;
	        }
	        // 开始
	        int i = 0;
	        int j = list.size()-1;
	        while(i<j) {
	        	list.get(i).next = list.get(j);
	        	i++;
	        	if(i==j) {
	        		break;
	        	}
	        	list.get(j).next = list.get(i);
	        	j--;
	        }
	        list.get(i).next = null;
	        
	    }
	    // 三步走解法法
	    // 找到原链表的起点
	    // 右半部分翻转
	    // 两个翻转
	    public void reorderList_2(ListNode head) {
	    	if(head==null) {
	    		return;
	    	}
	    	// 第一步求中间的点
	    	ListNode mid = middleNode(head);
	    	// 记录两个
	    	ListNode l1 = head;
	    	ListNode l2 = mid.next;
	    	mid.next = null;;
	    	// 翻转l2
	    	l2 = reverseList(l2);
	    	mergeList(l1,l2);
	    }
	    // 求中间的点
	    public ListNode middleNode(ListNode head) {
	    	ListNode slow = head;
	    	ListNode fast = head;
	    	while(fast!=null&&fast.next!=null) {
	    		fast = fast.next.next;
	    		slow = slow.next;
	    	}
	    	return slow;
	    }
	    // 翻转链表
	    public ListNode reverseList(ListNode head) {
	    	ListNode prev = null;
	    	ListNode curv = head;
	    	while(curv!=null) {
	    		ListNode next = curv.next;
	    		curv.next = prev;
	    		prev = curv;
	    		curv = next;
	    	}
	    	return prev;
	    }
	    // 合并
	    public void mergeList(ListNode l1,ListNode l2) {
	    	ListNode l1_temp;
	    	ListNode l2_temp;
	    	while(l1!=null&&l2!=null) {
	    		l1_temp = l1.next;
	    		l2_temp = l2.next;
	    		
	    		l1.next = l2;
	    		l1 = l1_temp;
	    		
	    		l2.next = l1;
	    		l2 = l2_temp;
	    	}
	    	
	    }
	}
}
