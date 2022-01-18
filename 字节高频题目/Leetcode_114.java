/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        if(root==null){
            return;
        }
       // 前序遍历展开
       Stack<TreeNode> stack = new Stack<>();
       // 存储结点
       List<TreeNode> list = new LinkedList<>();
       // 对其前序遍历
       stack.push(root);
       while(!stack.isEmpty()){
           root = stack.pop();
            list.add(root);

            if(root.right!=null){
                stack.push(root.right);
            }

            if(root.left!=null){
                stack.push(root.left);
            }
       }

       // 对链表进行操作
       for(int i=1;i<list.size();i++){
           TreeNode cur = list.get(i);
           TreeNode prev = list.get(i-1);
           prev.left = null;
           prev.right = cur;
       }

    }
}