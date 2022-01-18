/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
       
        ListNode fast = head;
        for(int i=1;i<k;i++){
            fast = fast.next;
        }

        ListNode slow = head;
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }   
}