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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 加法运算
        // 结果
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        // 进位
        int flag = 0;
        // 开始遍历 
        while(l1!=null || l2!=null){
            // 判别是否为空
            int v1 = l1==null?0:l1.val;
            int v2 = l2==null?0:l2.val;
            // 相加求和
            int temp_res = v1+v2+flag;

            // 新值赋值
            head.next = new ListNode(temp_res%10);
            head = head.next;
            flag = temp_res/10;

            // 接着走
            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }


        }

        if(flag!=0){
            head.next = new ListNode(flag);
        }

        return dummy.next;

    }
}