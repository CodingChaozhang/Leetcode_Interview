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
		public ListNode sortList(ListNode head) {
        return mergeSort(head,null);
    }
    public ListNode mergeSort(ListNode head,ListNode tail){
        // 截止条件
        if(head==null){
            return null;
        }
        if(head.next==tail){
            head.next = null;
            return head;
        }

        // 找mid
        // 快慢指针
        ListNode slow = head,fast = head;
        while(fast!=tail&&fast.next!=tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        ListNode left = mergeSort(head,mid);
        ListNode right = mergeSort(mid,tail);
        ListNode sorted =  merge(left,right);
        return sorted;
    }

    // 合并两个有序链表
    public ListNode merge(ListNode head1,ListNode head2){
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;

        while(head1!=null&&head2!=null){
            if(head1.val<head2.val){
                pre.next = head1;
                head1 = head1.next;
                pre = pre.next;
            }else{
                pre.next = head2;
                head2 = head2.next;
                pre = pre.next;
            }
        }

        pre.next = head1!=null?head1:head2;
        return dummy.next;
    }
}