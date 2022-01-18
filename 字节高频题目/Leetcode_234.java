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
    public boolean isPalindrome(ListNode head) {
        
        // O(n)的时间复杂度和O(1)的空间复杂度
        // 先去找中间的结点
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null&&fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 中间的结点
        ListNode mid = slow;
        // 前面的结点
        ListNode h1 = head;
        ListNode h2 = mid.next;
        // 断开
        mid.next = null;
        // 第二段翻转
        ListNode pre = null;
        ListNode next = null;
        while(h2!=null){
            next = h2.next;
            h2.next = pre;
            pre = h2;
            h2 = next;
        }
        // 第二端开始
        h2 = pre;
        //开始比较
        while(h1!=null&&h2!=null){
            if(h1.val!=h2.val){
                return false;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        return true;
    }
}