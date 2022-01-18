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
    public ListNode deleteDuplicates(ListNode head) {
        // 哑结点
        ListNode dummy = new ListNode(-1,head);
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null){
            // 如果重复的了
            while(fast!=null&&slow.val==fast.val){
                fast = fast.next;
            }
            slow.next = fast;
            slow  = slow.next;
        }
        return dummy.next;
    }
}