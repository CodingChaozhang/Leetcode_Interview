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
        // 添加一个哑结点
        ListNode dummy = new ListNode(-1,head);
        // 指针
        ListNode node = dummy;
        while(head!=null){
            while(head!=null&&head.val==val){
                // 相同
                head = head.next;
            }
            node.next = head;
            node = node.next;
            if(head!=null){
                head = head.next;
            }
        }
        return dummy.next;
    }
}