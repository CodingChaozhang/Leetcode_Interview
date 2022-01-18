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
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        // 递归
        dfs(root);
        return max;
    }
    // 求高度
    public int dfs(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftSize = dfs(root.left);
        int rightSize = dfs(root.right);
        // 更新直径
        max = Math.max(max,leftSize+rightSize);

        return Math.max(leftSize,rightSize) +1;

    }
}