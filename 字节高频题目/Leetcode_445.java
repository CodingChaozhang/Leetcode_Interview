/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 用arraylist来存储
        ArrayList<Integer> list_1 = new ArrayList<>();
        ArrayList<Integer> list_2 = new ArrayList<>();
        // 对其遍历
        int len_1 = 0;
        int len_2 = 0;
        while(l1!=null){
            len_1++;
            list_1.add(l1.val);
            l1 = l1.next;
        }
        while(l2!=null){
            len_2++;
            list_2.add(l2.val);
            l2 = l2.next;
        }
        int index_1 = len_1-1;
        int index_2 = len_2-1;
        int mod = 0;
        // 新链表
        ListNode node = null;
        while(index_1>=0 || index_2>=0){
            // 当前值
            int cur_1 = index_1<0?0: list_1.get(index_1);
            int cur_2 = index_2<0?0: list_2.get(index_2);
            // 总和
            int temp_sum = cur_1 + cur_2 + mod;
            // 计算
            mod = temp_sum/10;
            // 新节点
            if(node==null){
                // 第一个尾结点
                node = new ListNode(temp_sum%10);
            }else{
                ListNode newNode = new ListNode(temp_sum%10);
                newNode.next = node;
                // node重新移动
                node = newNode;
            }
 
            //继续走
            index_1--;
            index_2--;
        }
        //如果还有进位
        if(mod!=0){
            ListNode newNode = new ListNode(mod);
            newNode.next = node;
            node = newNode;
        }
        return node;

    }
}