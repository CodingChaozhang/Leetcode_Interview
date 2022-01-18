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
    public boolean isValidBST(TreeNode root) {
        if(root==null){
            return true;
        }
        // 用栈来解题
        long pre = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root!=null){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                if(pre!=Long.MIN_VALUE&&root.val<=pre){
                    return false;
                }
                // 赋值
                pre = root.val;
                root = root.right;
            }
        }
        return true;


    }
}