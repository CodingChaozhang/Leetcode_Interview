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
        // 两个链表来维护一个大的一个小的
        ListNode smallDummy = new ListNode(-1,head);
        ListNode small = smallDummy;
        ListNode bigDummy = new ListNode(-1,head);
        ListNode big = bigDummy;
        // 对其遍历
        while(head!=null){
            if(head.val<x){
                small.next = head;
                small = small.next;
            }else if(head.val>=x){
                big.next = head;
                big  = big.next;
            }
            head = head.next;
        }
        // 拼接
        big.next = null;
        small.next = bigDummy.next;
        return smallDummy.next;

    }
}