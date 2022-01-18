/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 解题思路：增加空间可用hashMap
        // 解题思路：不增加空间
        ListNode l1 = headA;
        ListNode l2 = headB;
        int len1 = 0;
        int len2 = 0;
        while(l1!=null){
            l1 = l1.next;
            len1++;
        }
        while(l2!=null){
            l2 = l2.next;
            len2++;
        }
        // 对其重新
        if(len1>=len2){
            l1 = headA;
            l2 = headB;
        }else{
            l1 = headB;
            l2 = headA;
        }

        // A较长 A先走
        int len = Math.abs(len1-len2);
        for(int i=1;i<=len;i++){
            l1 = l1.next;
        }

        // 同时走
        while(l1!=l2){
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;

    }
}