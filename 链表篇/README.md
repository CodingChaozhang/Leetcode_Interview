# 链表篇

## 双指针走

### [1.Leetcode206反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)


反转一个单链表。

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

**进阶:**
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

> 解题思路：两个指针，一个指向当前的指针前一个 一个指向当前指针的后一个，然后当前指针慢慢移动即可了。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
			ListNode next = null;
			while(head!=null) {
				// 先保存当前状态
				next = head.next;
				
				// 对当前节点处理 指向前一个
				head.next = pre;
				
				// 变量移动
				pre = head;
				
				head = next;
				
			}
			return pre;
    }
}
```

