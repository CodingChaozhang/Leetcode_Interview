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
    public int widthOfBinaryTree(TreeNode root) {
        // 宽度
        int width = 0;
        // 根节点
        root.val = 0;
         // 层次遍历解题
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 求宽度，计算索引
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            width = Math.max(width,queue.getLast().val-queue.getFirst().val+1);
            while(levelSize>0){
                root = queue.poll();
                if(root.left!=null){
                    root.left.val = root.val * 2 + 1;
                    queue.offer(root.left);
                }
                if(root.right!=null){
                    root.right.val = root.val * 2 + 2;
                    queue.offer(root.right);
                }
                levelSize--;
            }
        }

        return width;
    }
}