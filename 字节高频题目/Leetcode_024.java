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
    public ListNode swapPairs(ListNode head) {
       ListNode dummy = new ListNode(-1,head);
       ListNode newHead = dummy;
       while(newHead.next!=null&&newHead.next.next!=null){
           ListNode node1 = newHead.next;
           ListNode node2 = newHead.next.next;
           // 连接
           node1.next = node2.next;
           newHead.next = node2;
           node2.next = node1;
           // 移动到node1的位置
           newHead = node1;
       }
       return dummy.next; 
    }
}