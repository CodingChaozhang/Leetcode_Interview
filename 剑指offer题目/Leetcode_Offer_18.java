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
        // 哑结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 指针
        ListNode node = head;
        ListNode pre = dummy;
        while(node!=null){
            if(node.val==val){
                // 找到了
                break;
            }
            pre = node;
            node = node.next;
        }
        pre.next = node.next;
        return dummy.next;
    }
}