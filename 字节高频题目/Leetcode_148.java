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
        // 归并排序
        if(head==null || head.next==null){
            return head;
        }

        return mergeSort(head,null);
    }

    // 归并排序
    public ListNode mergeSort(ListNode head,ListNode tail){
        if(head==null){
            return null;
        }
        // 递归截止条件
        if(head.next==tail){
            head.next = null;
            return head;
        }
        // 找中间的点
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=tail&&fast.next!=tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        // 递归
        ListNode left = mergeSort(head,mid);
        ListNode right = mergeSort(mid,tail);
        //合并
        ListNode sorted = merge(left,right);
        return sorted;
    }

    // 合并
    public ListNode merge(ListNode l1,ListNode l2){
        // 新的链表
        ListNode dummy = new ListNode(-1);
        ListNode l = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                l.next = l1;
                l1 = l1.next;
                l = l.next;
            }else{
                l.next = l2;
                l2 = l2.next;
                l = l.next;
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