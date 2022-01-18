/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        // 正序遍历，逆序出来，考察栈
        Stack<Integer> stack = new Stack<>();
        // 对其遍历
        while(head!=null){
            stack.push(head.val);
            head = head.next;
        }
        // 逆序出来
        int[] res = new int[stack.size()];
        for(int i=0;i<res.length;i++){
            res[i] = stack.pop();
        }
        return res;
    }
}