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
    // 结果
    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        // 如果为空
        if(root==null){
            return res;
        }
        //层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 放入
        queue.offer(root);
        int levelSize = 1;
        // 开始
        while(!queue.isEmpty()){
            root = queue.poll();
            if(levelSize==1){
                res.add(root.val);
            }
            levelSize--;

            if(root.left!=null){
                queue.offer(root.left);
            }

            if(root.right!=null){
                queue.offer(root.right);
            }

            if(levelSize==0){
                levelSize = queue.size();
            }
        }
        return res;
    }
}