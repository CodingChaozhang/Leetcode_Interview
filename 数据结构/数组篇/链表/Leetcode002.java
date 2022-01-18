
public class Leetcode002 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(){
			
		}
		ListNode(int val){
			this.val = val;
		}
		ListNode(int val,ListNode next){
			this.val = val;
			this.next = next;
		}
	}
	class Solution {
		// 两数相加 按照模板
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        //新链表的
	    	ListNode head = null;
	    	ListNode tail = null;
	    	// 开始
	    	// 余数
	    	int flag = 0;
	    	// 总和
	    	int sum = 0;
	    	while(l1!=null||l2!=null) {
	    		int x = l1==null?0:l1.val;
	    		int y = l2==null?0:l2.val;
	    		// 总和
	    		sum = x + y + flag;
	    		// 开始赋值
	    		if(head==null) {
	    			head = tail = new ListNode(sum%10);
	    		}else {
	    			tail.next = new ListNode(sum%10); 
		    		tail = tail.next;
	    		}
	    		// 进
	    		flag = sum / 10;
	    		// 继续走
	    		if(l1!=null) {
	    			l1 = l1.next;
	    		}
	    		if(l2!=null) {
	    			l2 = l2.next;
	    		}
	    	}
	    	return head;
	    }
	}
}
