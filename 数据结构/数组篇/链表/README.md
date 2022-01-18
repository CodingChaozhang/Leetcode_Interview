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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1,head);
        ListNode l = dummy;
        // 记录原先的出现位置
        ListNode pre = dummy;
        ListNode next = dummy;
        // 记录目前链表的头和尾巴位置
        ListNode start = dummy;
        ListNode tail  = dummy;
        for(int i=1;i<left;i++){
            l = l.next;
        }
        pre = l;
        start = l.next;
        for(int i=left;i<=right;i++){
            l = l.next;
        }
        tail = l;
        next = l.next;
        // 反转链表
        ListNode newHead = reverse(start,next);
        // 连接
        pre.next = newHead;
        start.next = next;
        
        return dummy.next;
    }

    public ListNode reverse(ListNode head,ListNode tail){
        ListNode pre = null;
        ListNode next = null;
        while(head!=tail){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
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
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       ListNode dummy = new ListNode(-1);
	    	ListNode node = dummy;
	    	// 开始遍历
	    	int carry = 0;
	    	while(l1!=null||l2!=null) {
	    		int x = l1==null?0:l1.val;
	    		int y = l2==null?0:l2.val;
	    		
	    		int sum = x+y+carry;
	    		carry = sum/10;
	    		ListNode cur = new ListNode(sum%10);
	    		node.next = cur;
	    		node = cur;

                if(l1!=null) {
	    			l1 = l1.next;
	    		}
	    		if(l2!=null) {
	    			l2 = l2.next;
	    		}
	    		
	    	}
	    	// 如果还有剩余
	    	if(carry!=0) {
	    		node.next = new ListNode(carry);
	    	}
	    	return dummy.next;
    }
}
```

### [2.栈辅助解题-Leetcode445两数相加II](https://leetcode-cn.com/problems/add-two-numbers-ii/)

给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

 

进阶：

如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

 

示例：

输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 8 -> 0 -> 7

> 解题思路：用栈来辅助逆序计算

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 用栈来辅助解题
	    	Stack<Integer> s1 = new Stack<>();
	    	Stack<Integer> s2 = new Stack<>();
	    	while(l1!=null) {
	    		s1.push(l1.val);
	    		l1 = l1.next;
	    	}
	    	while(l2!=null) {
	    		s2.push(l2.val);
	    		l2 = l2.next;
	    	}
	    	// 余数
	    	int carry = 0;
	    	// 结果
	    	ListNode res = null;
	    	// 开始遍历
	    	while(!s1.isEmpty()||!s2.isEmpty()) {
	    		int x = s1.isEmpty()?0:s1.pop();
	    		int y = s2.isEmpty()?0:s2.pop();
	    		// 求和
	    		int sum = x+y+carry;
	    		carry = sum/10;
	    		// 结点
	    		ListNode curNode = new ListNode(sum%10);
	    		curNode.next = res;
	    		res = curNode;
	    	}
            if(carry!=0){
                ListNode curNode = new ListNode(carry);
	    		curNode.next = res;
	    		res = curNode;
            }
	    	return res;
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
    // 判断链表是否有环
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
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
    // 回文链表O(n)和O(1)的时间复杂度
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null){
            return true;
        }
        // 1.找到中间的结点
        ListNode mid = findMedium(head);
        // 2.找到下一段的开始
        ListNode next = mid.next;
        mid.next = null;
        // 翻转链表
        ListNode newHead = reverse(next);
        // 从头开始比较
        while(head!=null&&newHead!=null){
            if(head.val!=newHead.val){
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }
    // 中间的结点
    public ListNode findMedium(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // 翻转链表
    public ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
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
    // 翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null){
            return head;
        }
        // 记录
        ListNode a = head;
        ListNode b = head;
        for(int i=0;i<k;i++){
            if(b==null){
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a,b);
        // 连接上
        a.next = reverseKGroup(b,k);
        return newHead;
    }

    public ListNode reverse(ListNode head,ListNode tail){
        ListNode pre = null;
        ListNode next = null;
        while(head!=tail){
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
    // 环形链表II
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                fast = head;
                while(fast!=slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
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
        // 删除链表中的重复元素，不是连根去除，不需要哑结点
        ListNode cur = head;
        while(cur!=null&&cur.next!=null){
            //判断是否相等
            if(cur.val==cur.next.val){
                cur.next = cur.next.next;
            }else{
                // 不相等
                cur = cur.next;
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
    // 删除排序链表中的所有重复元素。连根拔除
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1,head);
        ListNode cur = dummy;
        while(cur.next!=null&&cur.next.next!=null){
            //判断
            if(cur.next.val==cur.next.next.val){
                int x = cur.next.val;
                // 去除所有的
                while(cur.next!=null&&cur.next.val==x){
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;
            }


        }

        return dummy.next;
    }
}
```

### [11.Leetcode328奇偶链表](https://leetcode-cn.com/problems/odd-even-linked-list/)

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:

输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
示例 2:

输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL

> 解题思路：用一个奇数链表和一个偶数链表走即可

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
    public ListNode oddEvenList(ListNode head) {
        // 判断
        if(head==null || head.next==null){
            return head;
        }
        // 开始记录
        ListNode l1 = head;
        ListNode l2 = head.next;
        ListNode l2_head = head.next;
        // 开始走
        while(l1.next!=null&&l2.next!=null){
            l1.next = l2.next;
            l1 = l1.next;
            l2.next = l1.next;
            l2 = l2.next;
        }
        // 重新连接
        l1.next = l2_head;
        return head;
    }
}
```

### [12.Leetcode086分隔链表](https://leetcode-cn.com/problems/partition-list/)

给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

 

示例：

输入：head = 1->4->3->2->5->2, x = 3
输出：1->2->2->4->3->5

> 解题思路：用两个链表来

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
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1,head);
        ListNode smallHead = small;
        ListNode big   = new ListNode(-1,head);
        ListNode bigHead = big;
        while(head!=null){
            if(head.val<x){
                small.next = head;
                small = small.next;
            }else{
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        // 加个null
        big.next = null;
        // 合并
        ListNode dummy = new ListNode(-1,smallHead.next);
        small.next = bigHead.next;

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
        ListNode dummy = new ListNode(-1);
        ListNode l = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val>l2.val){
                l.next = l2;
                l = l.next;
                l2 = l2.next;
            }else{
                l.next = l1;
                l = l.next;
                l1 = l1.next;
            }
        }
        if(l1!=null){
            l.next = l1;
        }
        if(l2!=null){
            l.next = l2;
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //哑结点
        ListNode dummy = new ListNode(-1,head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        // 开始走
        for(int i=1;i<=n;i++){
            fast = fast.next;
        }
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
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
    // 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1,head);
        ListNode l = dummy;
        // 开始走
        while(l.next!=null&&l.next.next!=null){
            ListNode first = l.next;
            ListNode second = l.next.next;
            // 交换
            first.next = second.next;
            second.next = first;
            l.next = second;
            l = first;
        }
        return dummy.next;
    }
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
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1,head);
        ListNode cur = dummy;
        while(cur.next!=null){
            if(cur.next.val==val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
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
    // 移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1,head);
        ListNode cur = dummy;
        while(cur.next!=null){
            // 判断
            if(cur.next.val==val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
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
    // 合并K个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        // 比较次序没定
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a,b)->(a.val-b.val));
        // 先加入进来
       for(int i=0;i<lists.length;i++){
           if(lists[i]!=null){
               queue.offer(lists[i]);
           }
       }
        // 新链表
        ListNode dummy = new ListNode(-1);
        ListNode l = dummy;
        //开始
        while(!queue.isEmpty()){
            ListNode node = queue.poll();
            // 添加进来
            l.next = node;
            l = l.next;
            // 添加该结点的结点
            if(node.next!=null){
                queue.offer(node.next);
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
    // 三步走战略
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null){
            return head;
        }
        // 1.先将其连成一个环
        ListNode tail = head;
        int len = 1;
        while(tail.next!=null){
            tail = tail.next;
            len++;
        }
        tail.next = head;
        // 2.计算新的k
        k = k % len;
        // 3.找到头和尾
        tail = head;
        for(int i=1;i<len-k;i++){
            tail = tail.next;
        }
        head = tail.next;
        tail.next = null;
        return head;
    }
}
```

### [2.Leetcode237删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)

请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。

 

现有一个链表 -- head = [4,5,1,9]，它可以表示为:

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/237_example.png)

示例 1：

输入：head = [4,5,1,9], node = 5
输出：[4,1,9]
解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

示例 2：

输入：head = [4,5,1,9], node = 1
输出：[4,5,9]
解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.

> 解题思路：传入的结点为待删除的结点

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
    public void deleteNode(ListNode node) {
        // 后一个覆盖前一个
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```

### [3.Leetcode707设计链表](https://leetcode-cn.com/problems/design-linked-list/)

设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。


示例：

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3

> 解题思路：最重要的是给了其一个size

```java
// 链表结点
class ListNode{
    int val;
    ListNode next;
    public ListNode(){

    }
    public ListNode(int val){
        this.val = val;
        this.next = null;
    }
    public ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }
}
class MyLinkedList {
    ListNode head;
    int size;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new ListNode(-1);
        size  = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index<0 || index>=size){
            return -1;
        }
        ListNode cur = head.next;
        for(int i=0;i<index;i++){
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
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index>size){
            return;
        }
        if(index<0){
            index = 0;
        }
        ListNode prev = head;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        // 创建新节点
        ListNode cur = new ListNode(val);
        cur.next = prev.next;
        prev.next = cur;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index<0 || index>=size){
            return;
        }
        ListNode prev = head;
        for(int i=0;i<index;i++){
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
```

### [4.Leetcode725分割链表](https://leetcode-cn.com/problems/split-linked-list-in-parts/)

给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。

每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。

这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。

返回一个符合上述规则的链表的列表。

举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]

示例 1：

输入: 
root = [1, 2, 3], k = 5
输出: [[1],[2],[3],[],[]]
解释:
输入输出各部分都应该是链表，而不是数组。
例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。

> 解题思路：一共k份，每份多少

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
    // 分割链表 
    public ListNode[] splitListToParts(ListNode root, int k) {
        // 1.先计算size
        ListNode head = root;
        int count = 0;
        while(head!=null){
            count++;
            head = head.next;
        }
        head = root;
        // 结果存储
        ListNode[] res = new ListNode[k];
        // 一共k份  每份大小为size/k; 余数为多少
        int size = count/k;
        int rem  = count%k;
        // 针对每一份
        for(int i=0;i<k;i++){
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            for(int j=0;j<size+(i<rem?1:0);j++){
                cur.next = new ListNode(root.val);
                cur = cur.next;
                if(root!=null){
                    root = root.next;
                }
            }
            res[i] = dummy.next;
        }
        return res;
    }
}
```

### [5.Leetcode1069合并两个链表](https://leetcode-cn.com/problems/merge-in-between-linked-lists/)



给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。

请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。

下图中蓝色边和节点展示了操作后的结果：


请你返回结果链表的头指针。

 ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/28/fig1.png)

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/28/merge_linked_list_ex1.png)

输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
输出：[0,1,2,1000000,1000001,1000002,5]
解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。

> 解题思路：找其头尾即可。

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
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // 删除节点＋哑结点
        ListNode dummy = new ListNode(-1,list1);
        //开始寻找结点
        ListNode pre = dummy;
        for(int i=0;i<a;i++){
            pre = pre.next;
        }
        // 再接着走
        ListNode next = pre;
        for(int i=a;i<=b;i++){
            next = next.next;
        }
        next = next.next;
        //对list2遍历
        ListNode h2 = list2;
        ListNode tail2 = list2;
        while(tail2.next!=null){
            tail2 = tail2.next;
        }
        // 合并
        pre.next = h2;
        tail2.next = next;

        return dummy.next;
    }
}
```

### [6.Leetcode1721交换链表中的节点](https://leetcode-cn.com/problems/swapping-nodes-in-a-linked-list/)

给你链表的头节点 head 和一个整数 k 。

交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。

 

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/10/linked1.jpg)

输入：head = [1,2,3,4,5], k = 2
输出：[1,4,3,2,5]

示例 2：

输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
输出：[7,9,6,6,8,7,3,0,9,5]



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
    public ListNode swapNodes(ListNode head, int k) {
       // 双指针的思路来找到倒数第k个结点
       ListNode fast = head;
       ListNode slow = head;
       ListNode first = null;
       ListNode second = null;
       for(int i=1;i<k;i++){
           fast = fast.next;
       }
       first = fast;
       while(fast.next!=null){
           slow = slow.next;
           fast = fast.next;
       }
       second = slow;
       // 交换的时候需要传链表的
       int temp = first.val;
       first.val = second.val;
       second.val = temp;
       return head;
    }
}
```

### [7.Leetcode817链表组件](https://leetcode-cn.com/problems/linked-list-components/)

给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。

同时给定列表 G，该列表是上述链表中整型值的一个子集。

返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。

 

示例 1：

输入: 
head: 0->1->2->3
G = [0, 1, 3]
输出: 2
解释: 
链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。

示例 2：

输入: 
head: 0->1->2->3->4
G = [0, 3, 1, 4]
输出: 2
解释: 
链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。

> 解题思路：用hashset。在hashset寻找该值和下一个值。包含该值，不包含下一个值，就可以计数

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
    //对于G中的所有元素构成多少个head相连的子链表呢？
    public int numComponents(ListNode head, int[] G) {
        HashSet<Integer> hashset = new HashSet<>();
        for(int num:G){
            hashset.add(num);
        }
        // 对其遍历
        int res = 0;
        while(head!=null){
            if(hashset.contains(head.val)&&( (head.next==null) || (!hashset.contains(head.next.val)) )){
                res++;
            }
            head = head.next;
        }
        return res;
    }
}
```



## 辅助结构解题

### [1.Leetcode143重排链表](https://leetcode-cn.com/problems/reorder-list/)

给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1:

给定链表 1->2->3->4, 重新排列为 1->4->2->3.
示例 2:

给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.

> 解题思路：用arraylist来辅助

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
    public void reorderList(ListNode head) {
        // 1.找中间链表
        ListNode mid = findMedium(head);
        // 2.划分成两个链表
        ListNode newHead = mid.next;
        mid.next = null;
        // 3.后面那个链表翻转
        newHead = reverse(newHead);
        //4.最后合并两个
        merge(head,newHead);

    }
    public ListNode findMedium(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 翻转链表
    public ListNode reverse(ListNode newHead){
        ListNode pre = null;
        ListNode next = null;
        while(newHead!=null){
            next = newHead.next;
            newHead.next = pre;
            pre = newHead;
            newHead = next;
        }
        return pre;
    }
    // 合并两个链表
    public void merge(ListNode before,ListNode after){
        ListNode next1 = null;
        ListNode next2 = null;
        while(before!=null&&after!=null){
            next1 = before.next;
            next2 = after.next;
            // 开始连接
            before.next = after;
            before = next1;

            after.next = before;
            after = next2;
        }
    }
}
```

> 解题思路：三步走：
>
> 找中点
>
> 左边；右边翻转
>
> 合并两个链表即可

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
}
```

### [2.Leetcode_Interview_02_01移除重复结点](https://leetcode-cn.com/problems/remove-duplicate-node-lcci/)

编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。

示例1:

 输入：[1, 2, 3, 3, 2, 1]
 输出：[1, 2, 3]
示例2:

 输入：[1, 1, 1, 1, 2]
 输出：[1, 2]
提示：

链表长度在[0, 20000]范围内。
链表元素在[0, 20000]范围内。

> 解题思路：用Hashset来辅助解题，注意前缀结点

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
    // 移除未排序链表中的重复节点，保留最开始出现的结点
    public ListNode removeDuplicateNodes(ListNode head) {
        // 用hashset来去重
        HashSet<Integer> hashset = new HashSet<>();
        // 新链表
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 指针
        ListNode l = dummy;
        // 对其遍历
        while(head!=null){
            //判断
            if(!hashset.contains(head.val)){
                // hashset为空或者不包含该值
                hashset.add(head.val);
                l.next = head;
                l = l.next;
                head = head.next;
            }else{
                // 包含该值
               head = head.next;
               l.next = head;
            }
        }
        return dummy.next;
    }
}
```

### [3.Leetcode1290二进制链表转整数](https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/)

给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。

请你返回该链表所表示数字的 十进制值 。

 

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/12/15/graph-1.png)

```
输入：head = [1,0,1]
输出：5
解释：二进制数 (101) 转化为十进制数 (5)
```

> 解题思路：用栈来辅助解题

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
    public int getDecimalValue(ListNode head) {
        // 还是要逆序来存储
        Stack<Integer> stack = new Stack<>();
        while(head!=null){
            stack.push(head.val);
            head = head.next;
        }
        int res = 0;
        int k = 0;
        int temp = 0;
        while(!stack.isEmpty()){
            temp = stack.pop() * (int)Math.pow(2,k++);
            res += temp;
        }
        return res;
    }
}
```



### [3.复制含有随机结点的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer/)

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的 深拷贝。 

我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：

val：一个表示 Node.val 的整数。
random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。


示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

> 解题思路：用hashmap辅助解题

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node,Node> hashMap = new HashMap<>();
        Node node = head;
        while(node!=null){
            hashMap.put(node,new Node(node.val));
            node = node.next;
        }
        node = head;
        while(node!=null){
            hashMap.get(node).next = hashMap.get(node.next);
            hashMap.get(node).random = hashMap.get(node.random);
            node = node.next;
        }
        return hashMap.get(head);
    }
}
```

### [4.判断一个链表是否为回文结构](https://leetcode-cn.com/problems/palindrome-linked-list-lcci/)

编写一个函数，检查输入的链表是否是回文的。

 

示例 1：

输入： 1->2
输出： false 
示例 2：

输入： 1->2->2->1
输出： true 

> 解题思路：用栈来辅助或者找到中间的点，之后逆转链表，然后同时从两边比较。

```java
public class Code_IsPalindromeList {
	// 定义一个结点
	class Node{
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	
	public boolean IsPalindromeList(Node head) {
		Node node = head;
		// 用栈来存储
		Stack<Node> stack = new Stack<>();
		// 对其遍历
		while(head!=null) {
			stack.push(head);
			head = head.next;
		}
		// 对栈和重新遍历
		while(node!=null) {
			if(node.val!=stack.pop().val) {
				return false;
			}
			node = node.next;
		}
		return true;
	}
}

```

### [5.Leetcode430扁平化多级双向链表](https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/)

多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。

 

示例 1：

输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
输出：[1,2,3,7,8,11,12,9,10,4,5,6]
解释：

输入的多级列表如下图所示：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/multilevellinkedlist.png)

扁平化后的链表如下图：


![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/multilevellinkedlistflattened.png)

> 解题思路：用栈来辅助

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    // 用栈来解决该问题由prev和next记录
    public Node flatten(Node head) {
        if(head==null){
            return head;
        }
        // val prev next和child
        Node dummy = new Node(-1,null,head,null);
        // 记录当前的指针
        Node cur = dummy;
        Node prev = dummy;
        // 用栈
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            cur = stack.pop();
            
            // 开始记录
            prev.next = cur;
            cur.prev = prev;
            if(cur.next!=null){
                stack.push(cur.next);
            }

            if(cur.child!=null){
                stack.push(cur.child);
                // 置为空
                cur.child = null;
            }
            prev = cur;
        }
        // 重新整理
        dummy.next.prev = null;
        return dummy.next;
    }
}
```

### [6.Leetcode1171从链表中删去综合值为零的连续节点](https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/)

给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。

删除完毕后，请你返回最终结果链表的头节点。

 

你可以返回任何满足题目要求的答案。

（注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）

示例 1：

输入：head = [1,2,-3,3,1]
输出：[3,1]
提示：答案 [1,2,1] 也是正确的。
示例 2：

输入：head = [1,2,3,-3,4]
输出：[1,2,4]
示例 3：

输入：head = [1,2,3,-3,-2]
输出：[1]

> 解题思路：前缀和。用hashmap来两次遍历。第一次遍历存储和和结点。第二次遍历找相同和的就可以过滤掉

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
    // 删去总和为0的连续结点
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l = dummy;
        // hashmap存储前缀和
        HashMap<Integer,ListNode> hashMap = new HashMap<>();
        int preSum = 0;
        // 遍历 
        while(l!=null){
            preSum += l.val;
            hashMap.put(preSum,l);
            //继续走
            l = l.next;
        }
        // 再次开始查找
        l = dummy;
        preSum = 0;
        while(l!=null){
            preSum += l.val;
            if(hashMap.containsKey(preSum)){
                l.next = hashMap.get(preSum).next;
            }
            l = l.next;
        }
        return dummy.next;
    }
}
```



### [5.两个单链表相交的问题]

本题中，单链表可能有环，也可能无环。给定两个单链表的头结点head1和head2，这两个链表可能相交，也可能不相交。请实现一个函数，如果这两个链表相交，请返回相交的第一个节点；如果不相交，返回null即可。

**判断是否有环**

```java
	// 结点
	class Node{
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	// 判断其是否有环，有环则返回环开始，无环返回空
	public static Node getLoopNode(Node head) {
		// 快慢指针来解决 该问题
		if(head==null||head.next==null) {
			return null;
		}
		Node low = head;
		Node fast = head;
		while(low!=fast) {
			if(low.next==null||fast.next.next==null) {
				return null;
			}
			low = low.next;
			fast = fast.next.next;
		}
		fast = head;
		while(low!=fast) {
			low = low.next;
			fast = fast.next;
		}
		return low;
	}
	// 判断其是否有环，有环则返回环开始，无环返回空
	public static Node getLoopNode_2(Node head) {
		// hashset来解决该问题
		HashSet<Node> hashSet = new HashSet<Node>();
		while(head!=null) {
			if(hashSet.contains(head)) {
				return head;
			}
			hashSet.add(head);
			head = head.next;
		}
		return null;
	}
```

**单链表若有环，必是下面的情形**

![image-20201116101943218](E:/笔记/数据结构与算法/algorithm/4.png)

**单链表相交的几种情况：**

- 两个无环的单链表

  - 相交

  ![image-20201116102637035](E:/笔记/数据结构与算法/algorithm/5.png)

  - 不相交

  ![image-20201116102649312](E:/笔记/数据结构与算法/algorithm/6.png)

  ```java
  	//两个无环的链表判断是否相交
  	public static Node noLoop(Node head1,Node head2) {
  		if(head1==null||head2==null) {
  			return null;
  		}
  		// 用hashSet来解决
  		HashSet<Node> hashSet = new HashSet<Node>();
  		while(head1!=null) {
  			hashSet.add(head1);
  			head1 = head1.next;
  		}
  		while(head2!=null) {
  			if(hashSet.contains(head2)) {
  				return head2;
  			}
  			head2 = head2.next;
  		}
  		return null;
  	}
  	// 两个无环的链表判断是否相交2
  	public static Node noLoop_2(Node head1,Node head2) {
  		if(head1==null||head2==null) {
  			return null;
  		}
  		Node cur1 = head1;
  		Node cur2 = head2;
  		int n = 0;
  		while(cur1.next!=null) {
  			n++;
  			cur1 = cur1.next;
  		}
  		while(cur2.next!=null) {
  			n--;
  			cur2 = cur2.next;
  		}
  		if(cur1!=cur2) {
  			return null;
  		}
  		cur1 = n>0?head1:head2;
  		cur2 = cur1==head1?head2:head1;
  		n = Math.abs(n);
  		while(n!=0) {
  			n--;
  			cur1 = cur1.next;
  		}
  		while(cur1!=cur2) {
  			cur1 = cur1.next;
  			cur2 = cur2.next;
  		}
  		return cur1;
  	}
  ```

  

- 一个有环一个无环

  - 必不可能相交

- 两个有环的情况

  ![image-20201116105022736](E:/笔记/数据结构与算法/algorithm/7.png)

```java
	// 判断有环的两个链表 是否相交
	public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		if(loop1==loop2) {
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while(cur1!=loop1) {
				n++;
				cur1 = cur1.next;
			}
			while(cur2!=loop2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n>0?head1:head2;
			cur2 = cur1==head1?head2:head1;
			n = Math.abs(n);
			while(n!=0) {
				n--;
				cur1 = cur1.next;
			}
			while(cur1!=cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		}else {
			cur1 = loop1.next;
			while(cur1!=loop1) {
				if(cur1==loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}
	// 判断其是否有环，有环则返回环开始，无环返回空
	public static Node getLoopNode(Node head) {
		// 快慢指针来解决 该问题
		if(head==null||head.next==null) {
			return null;
		}
		Node low = head;
		Node fast = head;
		while(low!=fast) {
			if(low.next==null||fast.next.next==null) {
				return null;
			}
			low = low.next;
			fast = fast.next.next;
		}
		fast = head;
		while(low!=fast) {
			low = low.next;
			fast = fast.next;
		}
		return low;
	}
	// 判断其是否有环，有环则返回环开始，无环返回空
	public static Node getLoopNode_2(Node head) {
		// hashset来解决该问题
		HashSet<Node> hashSet = new HashSet<Node>();
		while(head!=null) {
			if(hashSet.contains(head)) {
				return head;
			}
			hashSet.add(head);
			head = head.next;
		}
		return null;
	}
```

## 单调栈辅助解题

### [1.Leetcode1019链表中的下一个更大结点](https://leetcode-cn.com/problems/next-greater-node-in-linked-list/)

给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。

每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。

返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。

注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。

 

示例 1：

输入：[2,1,5]
输出：[5,5,0]
示例 2：

输入：[2,7,4,3,5]
输出：[7,0,5,5,0]
示例 3：

输入：[1,7,5,1,9,2,5,1]
输出：[7,9,9,9,0,5,0,0]

> 链表转为list，从而进行计算

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
    public int[] nextLargerNodes(ListNode head) {
        // 链表的下一个更大元素是什么
        Stack<Integer> s_data = new Stack<>();
        while(head!=null){
            s_data.push(head.val);
            head = head.next;
        }
        // 另外一个栈来作为单调栈
        Stack<Integer> s = new Stack<>();
        // 结果存储
        int[] res = new int[s_data.size()];
        int index = s_data.size()-1;
        while(!s_data.isEmpty()){
            int data = s_data.pop();
            // 出栈
            while(!s.isEmpty()&&data>=s.peek()){
                s.pop();
            }
            // 结果记录
            res[index--] = s.isEmpty()?0:s.peek();
            // 入栈
            s.push(data);
        }
        return res;
    }
}
```





## 递归思路

### [1.Leetcode109有序链表转换为二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/)

给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定的有序链表： [-10, -3, 0, 5, 9],

一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

```
   0
     / \
   -3   9
   /   /
 -10  5
```

> 递归思路来解题

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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 // 有序链表转为二叉搜索树
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return dfs(head,null);
    }

    public TreeNode dfs(ListNode head,ListNode tail){
        if(head==tail){
            return null;
        }
        // 中间的点
        ListNode medium = findMedium(head,tail);
        TreeNode root = new TreeNode(medium.val);
        root.left = dfs(head,medium);
        root.right = dfs(medium.next,tail);
        return root;

    }
    public ListNode findMedium(ListNode left,ListNode right){
        ListNode slow = left;
        ListNode fast = left;
        while(fast!=right&&fast.next!=right){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
```

### [2.Leetcode1367二叉树中的列表](https://leetcode-cn.com/problems/linked-list-in-binary-tree/)



给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。

如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。

一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。

 

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/02/29/sample_1_1720.png)

输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：true
解释：树中蓝色的节点构成了与链表对应的子路径。

> 递归解题思路

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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 二叉树中的列表 子树的解题思路
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(head==null){
            return true;
        }
        if(root==null){
            return false;
        }
        if(check(head,root)){
            return true;
        }
        return isSubPath(head,root.left) || isSubPath(head,root.right);
    }

    public boolean check(ListNode head,TreeNode root){
        // 第一个是
        if(head==null){
            return true;
        }
        if(root==null){
            return false;
        }
        if(head.val!=root.val){
            return false;
        }
        return check(head.next,root.left) || check(head.next,root.right);
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
    // 排序链表-归并排序
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        // 开始归并排序
        return mergeSort(head,null);
    }
    // 归并排序
    public ListNode mergeSort(ListNode head,ListNode tail){
        // 归并排序
        // 递归结束条件
        if(head.next==tail){
            head.next = null;
            return head;
        }
        // 找中间的结点
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=tail&&fast.next!=tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        // 归并排序
        ListNode left = mergeSort(head,mid);
        ListNode right = mergeSort(mid,tail);
        // 合并
        ListNode sorted = merge(left,right);
        return sorted;
    }
    // 两个链表合并
    public ListNode  merge(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode l = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val>l2.val){
                l.next = l2;
                l = l.next;
                l2 = l2.next;
            }else{
                l.next = l1;
                l = l.next;
                l1 = l1.next;
            }
        }
        if(l1!=null){
            l.next = l1;
        }
        if(l2!=null){
            l.next = l2;
        }
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

### 面试题-约瑟夫环问题]

据说著名犹太历史学家 Josephus有过以下的故事：在罗马人占领乔塔帕特后，39 个犹太人与Josephus及他的朋友躲到一个洞中，39个犹太人决定宁愿死也不要被敌人抓到，于是决定了一个自杀方式，41个人排成一个圆圈，由第1个人开始报数，每报数到第3人该人就必须自杀，然后再由下一个重新报数，直到所有人都自杀身亡为止。然而Josephus 和他的朋友并不想遵从。首先从一个人开始，越过k-2个人（因为第一个人已经被越过），并杀掉第k个人。接着，再越过k-1个人，并杀掉第k个人。这个过程沿着圆圈一直进行，直到最终只剩下一个人留下，这个人就可以继续活着。问题是，给定了和，一开始要站在什么地方才能避免被处决？Josephus要他的朋友先假装遵从，他将朋友与自己安排在第16个与第31个位置，于是逃过了这场死亡游戏。





```java
public static int lastRemaining(int n, int m) {
    if (n < 1 || m < 1) {
        return -1;
    }

    List<Integer> list = new LinkedList<>();
    for (int i = 1; i <=n; i++) {
        list.add(i);
    }

    // 要删除元素的位置
    int idx = 0;

    while (list.size() > 1) {

        // 只要移动m-1次就可以移动到下一个要删除的元素上
        for (int i = 1; i < m; i++) {
            idx = (idx + 1) % list.size(); // 【A】
        }

        list.remove(idx);

        // 确保idx指向每一轮的第一个位置
        // 下面的可以不用，【A】已经可以保证其正确性了，可以分析n=6，m=6的第一次删除情况
    //  if (idx == list.size()) {
    //      idx = 0;
    //  }
    }

    return list.get(0);
}
```

