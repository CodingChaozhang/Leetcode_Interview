// 有序链表转换为二叉搜索树
public class Leetcode109 {
	
	class Solution {
	    public TreeNode sortedListToBST(ListNode head) {
	    	// 排序好的找中点
	    	// 递归截止条件
	    	if(head==null) {
	    		return null;
	    	}
	    	if(head.next==null) {
	    		return new TreeNode(head.val);
	    	}
	    	// 开始找
	    	ListNode slow = head;
	    	ListNode fast = head;
	    	ListNode pre = null;
	    	while(fast!=null&&fast.next!=null) {
	    		fast = fast.next.next;
	    		pre = slow;
	    		slow = slow.next;
	    	}
	    	// 分割出左链表
	    	pre.next = null;
	    	// 分割出右链表
	    	ListNode rigthList = slow.next;
	    	// 中间用于构造根节点
	    	TreeNode root = new TreeNode(slow.val);
	    	root.left = sortedListToBST(head);
	    	root.right = sortedListToBST(rigthList);
	    	return root;
	    	
	    }
	}
}
