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
        //合并两个链表
        ListNode merge_head = new ListNode(-1);
        ListNode l = merge_head;
        // 对其遍历
        while(l1!=null&&l2!=null){
            // 比较
            if(l1.val<=l2.val){
                l.next = new ListNode(l1.val);
                l1 = l1.next;
            }else{
                l.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            l = l.next;
        }
        // 未遍历完
        if(l1!=null){
            l.next = l1;
        }
        if(l2!=null){
            l.next = l2;
        }
        return merge_head.next;

    }
}