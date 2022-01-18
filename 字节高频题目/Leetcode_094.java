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
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
       // 非递归的解决方法
       Stack<TreeNode> stack = new Stack<>();
       while(!stack.isEmpty() || root!=null){
           if(root!=null){
               stack.push(root);
               root = root.left;
           }else{
               root = stack.pop();
               res.add(root.val);
               root = root.right;
           }
       }
       return res;
    }
}