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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 开始
        ListNode dummy = new ListNode(-1,head);
        // 指针
        ListNode newHead = dummy;
        // 先走
        for(int i=1;i<left;i++){
            newHead = newHead.next;
        }
        //翻转
        ListNode cur = newHead.next;
        ListNode pre = null;
        ListNode next = null;
        // 翻转停止条件
        for(int i=left;i<=right;i++){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 此时cur为指向的下一个 
        // 重新串联起来
        newHead.next.next = cur;
        newHead.next = pre;
        return dummy.next;
    }
}