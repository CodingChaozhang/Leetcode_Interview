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

### [1.Leetcode092反转链表II](https://leetcode-cn.com/problems/reverse-linked-list-ii/)

反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL

> 反转链表添加一个哑结点

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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 哑结点
	    	ListNode dummy = new ListNode(-1,head);
	    	ListNode superior = dummy;
	    	for(int i=1;i<m;i++) {
	    		superior = superior.next;
	    	}
	    	ListNode cur = superior.next;
	    	ListNode prev = null;
	    	// 开始转换
	    	for(int i=m;i<=n;i++) {
	    		ListNode next = cur.next;
	    		cur.next = prev;
	    		prev = cur;
	    		cur = next;
	    	}
	    	// 更改m和m-n结点
	    	superior.next.next = cur;
	    	superior.next = prev;
	    	return dummy.next;
    }
}
```





### [2.Leetcode002两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/02/addtwonumber1.jpg)

示例 1：


输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.

示例 2：

输入：l1 = [0], l2 = [0]
输出：[0]
示例 3：

输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]

> 解题思路：加法的模板，此外注意head和tail的走法

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
```



### [3.Leetcode141环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)

给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

进阶：

你能用 O(1)（即，常量）内存解决此问题吗？



示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。

> 解题思路：快慢指针，一同出发，但是结束的条件需要好好看下。

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null) {
	    		return false;
	    	}	    		
	    	ListNode fast = head;
	    	ListNode low = head;
	    	while(fast.next!=null&&fast.next.next!=null) {
	    		fast = fast.next.next;
	    		low = low.next;
		    	// 对其判断
	    		if(low==fast) {
	    			return true;
	    		}
	    	}
	    	return false;
    }
}
```

### [4.Leetcode234回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/)

请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false

示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

> 解题思路：利用环形链表+反转链表的思路来解题

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
    public boolean isPalindrome(ListNode head) {

        if(head==null || head.next == null) {
	    		return true;
	    	}
        // 双指针来判断
	    	ListNode fast = head;
	    	ListNode low  = head;
	    	while(fast.next!=null&&fast.next.next!=null) {
	    		low = low.next;
	    		fast = fast.next.next;
	    	}
	    	
	    	// 分成两个链表
	    	fast = low.next;
	    	low.next = null;
	    	// 将后面那个链表反转
	    	ListNode pre = null;
	    	ListNode next = null;
	    	while(fast!=null) {
	    		next = fast.next;
	    		fast.next = pre;
	    		pre = fast;
	    		fast = next;
	    	}
	    	// 其头结点改为pre 另外一个头结点为head
	    	while(head!=null&&pre!=null) {
	    		if(pre.val!=head.val) {
	    			return false;
	    		}
	    		pre = pre.next;
	    		head = head.next;
	    	}
	    	return true;
    }
}
```

### [5.Leetcode025 K个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

 

示例：

给你这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

> 翻转链表，并对多个翻转后的链表进行合并，就是确定一个链表的开始和结尾

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
     // 多个k组合并成一个，一个成多个
		public ListNode reverseKGroup(ListNode head, int k) {
	    	ListNode dummy = new ListNode(-1,head);
	    	ListNode pre = dummy;
	    	ListNode tail = dummy;
	    	// 对其遍历
	    	while(head!=null) {
	    		for(int i=0;i<k;i++) {
	    			tail = tail.next;
	    			if(tail==null) {
	    				return dummy.next;
	    			}
	    		}
	    		ListNode start = pre.next;
	    		ListNode end = tail.next;
	    		tail.next = null;
	    		// 翻转
	    		pre.next = myReverse(start);
	    		// 重新开始
	    		start.next = end;
	    		pre = start;
	    		tail = pre;
	    		
	    	}
	    	
	    	return dummy.next;
	    }
		
		// 翻转
		public ListNode myReverse(ListNode head) {
			ListNode pre = null;
			ListNode next = null;
			while(head!=null) {
				next = head.next;
				head.next = pre;
				pre = head;
				head = next;
			}
			return pre;
					
		}
}
```

### [6.Leetcode876链表的中间结点](https://leetcode-cn.com/problems/middle-of-the-linked-list/)

给定一个头结点为 head 的非空单链表，返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

 

示例 1：

输入：[1,2,3,4,5]
输出：此列表中的结点 3 (序列化形式：[3,4,5])
返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
注意，我们返回了一个 ListNode 类型的对象 ans，这样：
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.

示例 2：

输入：[1,2,3,4,5,6]
输出：此列表中的结点 4 (序列化形式：[4,5,6])
由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。

> 解题思路：两个指针一起走

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        // 双指针
	    	ListNode fast = head;
	    	ListNode low = head;
            int len = 0;
	    	// 一起走
	    	while(fast!=null&&fast.next!=null) {
	    		fast = fast.next.next;
	    		low  = low.next;
	    	}
	    	return low;
    }
}
```

### [7.Leetcode160相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)

编写一个程序，找到两个单链表相交的起始节点。

如下面的两个链表：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。

 

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_1.png)

输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

> 解题思路：用两个指针来遍历即可。

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) {
	        	return null;
	        }
	        ListNode p1 = headA,p2 = headB;
	        //开始遍历
	        while(p1!=p2) {
	        	p1 = p1==null?headB:p1.next;
	        	p2 = p2==null?headA:p2.next;
	        }
	        return p1;
    }
}
```

### [8.Leetcode142环形链表II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

说明：不允许修改给定的链表。

进阶：

你是否可以使用 O(1) 空间解决此题？


示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

```
输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
```

> 解题思路：双指针走

```java
package com.lcz.leetcode;

public class Leetcode0142 {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
			next = null;
		}
	}
	public class Solution {
	    // 双指针
		public ListNode detectCycle(ListNode head) {
			if(head==null||head.next==null) {
				return null;
			}
	        ListNode low = head;
	        ListNode fast = head;
	        // 是否有环形
	        boolean isCycle = false;
	        // 双指针走
	        while(fast.next!=null&&fast.next.next!=null) {
	        	fast = fast.next.next;
	        	low  = low.next;
	        	if(low==fast) {
	        		isCycle = true;
	        		break;
	        	}
	        }
	        // 如果有环形
	        if(isCycle) {
	        	fast = head;
	        	while(fast!=low) {
	        		low = low.next;
	        		fast = fast.next;
	        	}
	        	return low;
	        	
	        }else {
	        	return null;
	        }
	    }
	}
}

```

### [9.Leetcode083删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:

输入: 1->1->2
输出: 1->2
示例 2:

输入: 1->1->2->3->3
输出: 1->2->3

> 删除重复元素，一个维护原数组的指针，一个维护新数组的指针

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
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next == null) {
	    		return head;
	    	}
	    	ListNode slow = head;
	    	ListNode fast = head;
	    	while(fast!=null) {
	    		// 重复的话就一直走
	    		while(fast!=null&&slow.val == fast.val) {
	    			fast = fast.next;
	    		}
	    		// 不重复了
	    		slow.next = fast;
	    		slow = fast;
                if(fast!=null){
	    		    fast = fast.next;
                }
	    	}
	    	return head;
    }
}
```

### [10.Leetcode082删除排序链表中的重复元素II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)

给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:

输入: 1->2->3->3->4->4->5
输出: 1->2->5

示例 2:

输入: 1->1->1->2->3
输出: 2->3

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
    public ListNode deleteDuplicates(ListNode head) {
       //哑结点
	    	ListNode dummy = new ListNode(-1,head);
	    	// 快慢指针解题
	    	ListNode slow = dummy;
	    	ListNode fast = head;
	    	while(fast!=null) {
	    		// 重复元素了
	    		if(fast.next!=null&&fast.val==fast.next.val) {
	    			// 就一直走
	    			while(fast.next!=null&&fast.val==fast.next.val) {
	    				fast = fast.next;
	    			}
	    			fast = fast.next;
	    			slow.next = fast;
	    		}else {
	    			// 没有重复元素
	    			slow = slow.next;
	    			fast = fast.next;
	    		}
	    	}
	    	return dummy.next;
    }
}
```



## 哑结点辅助

### [1.Leetcode021合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 



示例 1：

![img](https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg)

输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

示例 2：

输入：l1 = [], l2 = []
输出：[]

> 解题思路：新建一个链表，如何走

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // 新链表
	    	ListNode dummy = new ListNode(-1);
	    	ListNode head = dummy;
	    	// 开始走
	    	while(l1!=null&&l2!=null) {
	    		if(l1.val<l2.val) {
	    			head.next = l1;
		    		l1 = l1.next;
	    		}else {
	    			head.next = l2;
		    		l2 = l2.next;
	    		}
	    		head = head.next;
	    	}
	    	// 如果还有剩余
	    	if(l1!=null) {
	    		head.next = l1;
	    	}
	    	if(l2!=null) {
	    		head.next = l2;
	    	}
	    	return dummy.next;
    }
}
```

### [2.Leetcode019删除链表的倒数第N个结点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)

给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。

**进阶：**你能尝试使用一趟扫描实现吗？

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg)

输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：

输入：head = [1], n = 1
输出：[]

示例 3：

输入：head = [1,2], n = 1
输出：[1]

> 解题思路：删除结点的时候增加一个哑结点，并且用快慢指针的思路，从哪里开始，到哪里结束

```java
// 删除链表的倒数第N个结点
	    public ListNode removeNthFromEnd(ListNode head, int n) {
	    	// 删除结点用哑结点
	    	ListNode dummy = new ListNode(0,head);
	    	// 解法用快慢指针
	    	ListNode slow = dummy;
	    	ListNode fast = dummy;
	    	// 开始走n步
	    	for(int i=0;i<n;i++) {
	    		fast = fast.next;
	    	}
	    	// 一起走
	    	while(fast.next!=null) {
	    		fast = fast.next;
	    		slow = slow.next;
	    	}
	    	// 删除
	    	slow.next = slow.next.next;
	    	return dummy.next;
	    }
```

### [3.Leetcode024两两交换链表中的结点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg)


输入：head = [1,2,3,4]
输出：[2,1,4,3]
示例 2：

输入：head = []
输出：[]

> 解题思路：两个链表，新建一个哑结点来辅助解题

```java
// 递归方法
	    public ListNode swapPairs(ListNode head) {
	    	// 递归结束的条件
	    	if(head==null || head.next==null) {
	    		return head;
	    	}
	    	ListNode newHead = head.next;
	    	head.next = swapPairs(newHead.next);
	    	newHead.next = head;
	    	return newHead;
	    }
```



```java
// 迭代方法
	    public ListNode swapPairs_2(ListNode head) {
	    	 ListNode dummy = new ListNode(-1,head);
		    	ListNode newHead = dummy;
		    	// 开始遍历
		    	while(newHead.next!=null&&newHead.next.next!=null) {
		    		ListNode node1 = newHead.next;
		    		ListNode node2 = newHead.next.next;
		    		// 交换
		    		newHead.next = node2;
		    		node1.next = node2.next;
		    		node2.next = node1;
		    		// 移动
		    		newHead = node1;
		    	}	    	
		    	return dummy.next;
	    }
```

### [4.Leetcode_Offer_18 删除链表的节点](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

注意：此题对比原题有改动

示例 1:

输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

示例 2:

输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.

> 解题思路：添加一个哑结点，增加一个前置节点即可。

```java
package com.lcz.leetcode;
// 删除链表的结点
public class Leetcode_Offer_18 {
	// 结点
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
		
	}
	class Solution {
	    public ListNode deleteNode(ListNode head, int val) {
	    	// 开一个哑结点
	    	ListNode dummy = new ListNode(-1);
	    	dummy.next = head;
	    	ListNode prev = dummy;
	    	ListNode cur = head;
	    	while(cur!=null) {
	    		// 看是否
	    		if(cur.val == val) {
	    			prev.next = cur.next;
	    			break;
	    		}
	    		// 不相等
	    		prev = cur;
	    		cur = cur.next;
	    	}
	    	return dummy.next;
	    }
	}
}

```

### [5.Leetcode203移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/)


删除链表中等于给定值 ***val\*** 的所有节点。

**示例:**

```
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
```

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
    public ListNode removeElements(ListNode head, int val) {
        // 哑结点
	    	ListNode dummy = new ListNode(-1,head);
	    	ListNode prev = dummy;
	    	ListNode cur = head;
	    	while(cur!=null) {
	    		// 相等
	    		if(cur.val==val) {
	    			// 相等的话
	    			prev.next = cur.next;
	    			cur = cur.next;
	    		}else {
	    			prev.next = cur;
	    			cur = cur.next;
	    			prev = prev.next;
	    		}
	    		
	    	}
	    	return dummy.next;
    }
}
```



## 哑结点和优先级队列辅助

### [1.Leetcode023合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

 

示例 1：

输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6

> 优先级队列来辅助解题

```java
class Solution {
		
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
```

## 逻辑解题

### [1.Leetcode061旋转链表](https://leetcode-cn.com/problems/rotate-list/)

给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
示例 2:

输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL

> 解题思路：使其成为一个环，然后找到要移动的开始点，之后断开即可。

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
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next == null) {
	    		return head;
	    	}
	    	// 1.使其变为环形链表
	    	ListNode tail = head;
	    	int len = 1;
	    	while(tail.next!=null) {
	    		len++;
	    		tail = tail.next;
	    	}
	    	tail.next = head;
	    	// 2.到尾结点这里
	    	k = k % len;
	    	for(int i=0;i<len-k;i++) {
	    		tail = tail.next;
	    	}
	    	//3.找到头结点
	    	head = tail.next;
	    	tail.next = null;
	    	return head;
    }
}
```

## 排序

### [归并排序-数组]

```java
public class MergeSort {
	public static void mergeSort(int[] arr) {
		if(arr==null||arr.length<2) {
			return;
		}
		sortProcess(arr,0,arr.length-1);
	}
	
	public static void sortProcess(int[] arr,int L,int R) {
		if(L==R){
			return;
		}
		int mid = L + ((R-L)>>1);
		sortProcess(arr, L, mid);
		sortProcess(arr, mid+1, R);
		merge(arr,L,mid,R);
	}
	
	public static void merge(int[] arr,int L,int mid,int R) {
		int p1 = L;
		int p2 = mid+1;
		int[] temp = new int[R-L+1];
		int i=0;
		while(p1<=mid&&p2<=R) {
			temp[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
		}
		while(p1<=mid) {
			temp[i++] = arr[p1++];
		}
		while(p2<=R) {
			temp[i++] = arr[p2++];
		}
		for(int j=0;j<temp.length;j++) {
			arr[L+j] = temp[j];
		}
	}
	
	// 主函数
	public static void main(String[] args) {
		int[] arr = {56,9,10,19,28,37,34};
		mergeSort(arr);
		for(int number:arr) {
			System.out.print(number + " ");
		}
	}
}

```



### [归并排序-1.Leetcode148排序链表](https://leetcode-cn.com/problems/sort-list/)

给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

示例 1：

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg)

输入：head = [4,2,1,3]
输出：[1,2,3,4]

> 解题思路1：归并排序，这里求中间结点的方式有所不同了。

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
		public ListNode sortList(ListNode head) {
        return mergeSort(head,null);
    }
    public ListNode mergeSort(ListNode head,ListNode tail){
        // 截止条件
        if(head==null){
            return null;
        }
        if(head.next==tail){
            head.next = null;
            return head;
        }

        // 找mid
        // 快慢指针
        ListNode slow = head,fast = head;
        while(fast!=tail&&fast.next!=tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        ListNode left = mergeSort(head,mid);
        ListNode right = mergeSort(mid,tail);
        ListNode sorted =  merge(left,right);
        return sorted;
    }

    // 合并两个有序链表
    public ListNode merge(ListNode head1,ListNode head2){
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;

        while(head1!=null&&head2!=null){
            if(head1.val<head2.val){
                pre.next = head1;
                head1 = head1.next;
                pre = pre.next;
            }else{
                pre.next = head2;
                head2 = head2.next;
                pre = pre.next;
            }
        }

        pre.next = head1!=null?head1:head2;
        return dummy.next;
    }
}
```

### [快速排序-数组]

```java
public static void quickSort(int[] arr) {
		if(arr==null||arr.length<2) {
			return;
		}
		quickSort(arr,0,arr.length-1);
	}
	
	public static void quickSort(int[] arr,int L,int R) {
		if(L<R) {
            // 随机排序
            //swap(arr,L+(int)(Math.random()*(R-L+1)),R);
			int[] p = partition(arr,L,R);
			quickSort(arr,L,p[0]-1);
			quickSort(arr,p[1]+1,R);
		}
	}
	
	public static int[] partition(int[] arr,int L,int R) {
		int less = L-1;
		int more = R;
		while(L<more) {
			if(arr[L]<arr[R]) {
				swap(arr,++less,L++);
			}else if(arr[L]>arr[R]) {
				swap(arr,--more,L);
			}else{
				L++;
			}
		}
		swap(arr,more,R);
		return new int[] {less+1,more};
	}
	
```

### [快速排序-链表]

```java
class Solution {
		// O(nlogn)的时间复杂度 归并排序
	    public ListNode sortList(ListNode head) {
	    	// 判断
	    	if(head==null || head.next==null) {
	    		return head;
	    	}
	    	ListNode dummy = new ListNode(-1);
	    	dummy.next = head;
	    	return quickSort(dummy,null);
	    }	
	    
	    public ListNode quickSort(ListNode head,ListNode tail) {
	    	if(head==tail||head.next==tail||head.next.next==tail) {
	    		return head;
	    	}
	    	// 将小于划分点的值存放在临时链表中
	    	ListNode tempHead = new ListNode(-1);
	    	ListNode tp = tempHead;//指针
	    	ListNode partitaion = head.next;//划分点
	    	ListNode p = head.next; // 遍历的指针
	    	
	    	// 开始
	    	while(p.next!=tail) {
	    		// 小于
	    		if(p.next.val<partitaion.val) {
	    			tp.next = p.next;
	    			tp = tp.next;
	    			p.next = p.next.next;
	    		}else {
	    			p = p.next;
	    		}
	    	}
	    	
	    	// 合并临时链表和原链表
	    	tp.next = head.next;
	    	// 将临时链表
	    	head.next = tempHead.next;
	    	// 最后
	    	quickSort(head, partitaion);
	    	quickSort(partitaion, tail);
	    	return head.next;
	    }
	}
```

### [插入排序-数组]

```java
public class InsertSort {
	public static void insertSort(int[] arr) {
		if(arr==null || arr.length<2) {
			return;
		}
		for(int i=1;i<arr.length;i++) {
			for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--) {
				swap(arr,j,j+1);
			}
		}
	}
	
	public static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i]   = arr[j];
		arr[j]   = temp;
	}
}

```

### [插入排序-链表](https://leetcode-cn.com/problems/insertion-sort-list/)

```java
public ListNode insertionSortList(ListNode head) {
        // 链表
        if(head==null||head.next==null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        while(head!=null&&head.next!=null){
            // 先找到没排好序的
            if(head.val<=head.next.val){
                head = head.next;
                continue;
            }
            // 然后对其找前后
            ListNode pre = dummy;
            while(pre.next.val<head.next.val)pre=pre.next;
            ListNode cur = head.next;
            //换指针
            head.next = cur.next;
            cur.next  = pre.next;
            pre.next = cur;
        }
        return dummy.next;
    }
```

