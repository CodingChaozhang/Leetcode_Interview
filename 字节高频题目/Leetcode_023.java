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
    
    public ListNode mergeKLists(ListNode[] lists) {
        // 合并K个升序链表，topK问题，用优先级队列 自定义排序 建立最小
        PriorityQueue<ListNode> queue = new PriorityQueue<>((l1,l2)->(l1.val-l2.val));
       //对其存入值
       for(int i=0;i<lists.length;i++){
           // 判断是否为空
           if(lists[i]!=null){
             queue.offer(lists[i]);
           }
       }
       // 结果
       ListNode dummy = new ListNode(-1);
       ListNode head  = dummy;
       // 对其遍历
       while(!queue.isEmpty()){
           // 建立新节点
           ListNode curNode = queue.poll();
           head.next = new ListNode(curNode.val);
           head = head.next;
           // 排出来的这个节点是否还有
           if(curNode.next!=null){
               curNode = curNode.next;
               queue.offer(curNode);
           }
       }
       return dummy.next;
    }
}