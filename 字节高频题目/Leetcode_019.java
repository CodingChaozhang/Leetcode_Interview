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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 增加一个哑结点即可
        ListNode dummy = new ListNode(-1,head);
        // 两个头指针
        ListNode fast = dummy;
        ListNode slow = dummy;
        // 开始走
        for(int i=1;i<=n;i++){
            fast = fast.next;
        }
    
        // 一起走
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        // 找到了待删的前一个
        slow.next = slow.next.next;
        return dummy.next;
    }
}