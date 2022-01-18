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
    // 层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        // 每一层的存储
        List<Integer> list = new ArrayList<>();
        // 队列来辅助
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 每一层的结点数
        int levelSize = 1;
        while(!queue.isEmpty()){
            root = queue.poll();
            levelSize--;
            list.add(root.val);

            if(root.left!=null){
                queue.offer(root.left);
            }

            if(root.right!=null){
                queue.offer(root.right);
            }

            // 层数为0
            if(levelSize==0){
                res.add(list);
                levelSize = queue.size();
                list = new ArrayList<>();

            }
        }
        return res;
    }
}