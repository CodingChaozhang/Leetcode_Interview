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
        // 1.找中间的结点
        ListNode mid = findMiddle(head);
        // 2.划分两个结点
        ListNode after = mid.next;
        // 使其指针置为null
        mid.next = null;
        // 使后面那个链表翻转
        after = reverse(after);
        // 3.使前面那个链表和后面那个链表合并
        merge(head,after);
    }

    // 找中间的点
    public ListNode findMiddle(ListNode head){
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 翻转
    public ListNode reverse(ListNode cur){
        ListNode pre = null;
        ListNode next = null;
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }        
        return pre;
    }

    // 合并
    public void merge(ListNode before,ListNode after){
        // 合并
        ListNode next1;
        ListNode next2;
        while(before!=null&&after!=null){
            next1 = before.next;
            next2 = after.next;

            before.next = after;
            before = next1;

            after.next = before;
            after = next2;

        }

    }
}