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
        ListNode slow = dummy;
        ListNode fast = head;
        // 开始
        while(fast!=null){
            // 遇到重复了
            if(fast.next!=null&&fast.val==fast.next.val){
                // 多走几步
                while(fast.next!=null&&fast.val==fast.next.val){
                    fast = fast.next;
                }
                fast = fast.next;
                slow.next = fast;
            }else{
                // 不重复了
                slow = slow.next;
                fast = fast.next;
            }

        }

        return dummy.next;
    }
}