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
    public boolean isCompleteTree(TreeNode root) {
        // 解题思路 层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 判断当前节点是否为叶子
        boolean leaf = false;
        // 开始遍历
        while(!queue.isEmpty()){
            root = queue.poll();
            if(leaf&&!isLeaf(root)){
                return false;
            }
            if(root.left!=null){
                queue.offer(root.left);
            }else if(root.right!=null){
                return false;
            }

            if(root.right!=null){
                queue.offer(root.right);
            }else{
                leaf = true;
            }

        }
        return true;
    }

    // 判断是否叶子结点
    public boolean isLeaf(TreeNode root){
        return root.left==null&&root.right==null;
    }
}