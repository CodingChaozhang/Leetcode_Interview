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
    public ListNode reverseKGroup(ListNode head, int k) {
        // 分段
        ListNode dummy = new ListNode(-1,head);
        // 给出一个头一个尾巴
        ListNode pre = dummy;
        ListNode tail = dummy;
        while(pre!=null){
            // 先走k
            for(int i=0;i<k;i++){
                tail = tail.next;
                if(tail==null){
                    return dummy.next;
                }
            }
            //对其每段的记录
            ListNode start = pre.next;
            ListNode end = tail.next;
            // k断开
            tail.next = null;
            // 新链表连接上
            pre.next = reverse(start);
            // k断开重连
            start.next = end;
            pre = start;
            tail = start;

        }
        return dummy.next;
    }

    // 给一个头结点翻转出
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